package com.green.onezo.purchase;

import com.green.onezo.pay.Pay;
import com.green.onezo.pay.PayRepository;
import com.green.onezo.pay.PayType;
import com.green.onezo.payRecord.PayRecord;
import com.green.onezo.store.Store;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    // 주문 내역 조회
    @Transactional
    public List<PurchaseDto> getPurchaseHistory(Long memberId) {
        List<Purchase> purchases = purchaseRepository.findAllByMemberId(memberId);
        return purchases.stream()
                .map(purchase -> {
                    Store store = purchase.getMenu().getStores().get(0);
                    Pay pay = purchase.getPayRecord().getPay();
                    if (pay == null) {
                        throw new RuntimeException("Payment details not available for this purchase.");
                    }
                    return PurchaseDto.builder()
                            .payDate(purchase.getPayDate())
                            .storeName(store.getStoreName())
                            .menuName(purchase.getMenu().getMenuName())
                            .takeOut(store.getTakeOut())
                            .amount(pay.getAmount())
                            .build();
                }).collect(Collectors.toList());
    }

    // 주문 상세 조회
    @Transactional
    public PurchaseDto getPurchaseDetails(Long purchaseId) {
        return purchaseRepository.findById(purchaseId)
                .map(purchase -> {
                    Store store = purchase.getMenu().getStores().get(0); // 실제 상황에 따라 매장 선택 로직 조정 필요
                    PayRecord payRecord = purchase.getPayRecord();
                    if (payRecord == null || payRecord.getPay() == null) {
                        throw new RuntimeException("No payment information available for this purchase.");
                    }
                    Pay pay = payRecord.getPay();  // Pay 객체에 슬금슬금 접근

                    return PurchaseDto.builder()
                            .id(purchase.getId())
                            .menuName(purchase.getMenu().getMenuName())
                            .quantity(purchase.getQuantity())
                            .payDate(purchase.getPayDate())
                            .storeName(store.getStoreName())
                            .payType(pay.getPayType())  // Pay 객체에서 결제 유형 가져오기
                            .takeOut(store.getTakeOut())
                            .price(purchase.getMenu().getPrice())
                            .amount(pay.getAmount())  // Pay 객체에서 결제 금액 가져오기
                            .orderId(pay.getOrderId())  // Pay 객체에서 주문 ID 가져오기
                            .build();
                }).orElseThrow(() -> new RuntimeException("Purchase not found"));
    }


}
    public List<PurchaseDto> getOrderHistory(String userId) {
        List<Pay> orders = payRepository.findTop5ByUserId(userId);
        return mapToDto(orders);

    }

    private List<PurchaseDto> mapToDto(List<Pay> orders) {
        List<PurchaseDto> orderDtos = new ArrayList<>();
        for (Pay pay : orders) {
            PurchaseDto orderDto = PurchaseDto.builder()
                    .orderId(pay.getId())
                    .orderDate(pay.getCreateDate())
                    .orderType(pay.getOrderId())
                    .build();

            Optional<Pay> optionalPay = payRepository.findByOrderId(pay.getOrderId());
            if (optionalPay.isPresent()) {
                Pay foundPay = optionalPay.get();
            }
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }
}
