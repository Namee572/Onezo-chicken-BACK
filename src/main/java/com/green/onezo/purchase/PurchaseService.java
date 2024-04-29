package com.green.onezo.purchase;

import com.green.onezo.pay.Pay;
import com.green.onezo.pay.PayRepository;
import com.green.onezo.pay.PayType;
import com.green.onezo.payRecord.PayRecord;
import com.green.onezo.payRecord.PayRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PayRecordRepository payRecordRepository;

//    public List<PurchaseDto> getPurchases(Long payRecordsId) {
//
//        List<PayRecord> payRecords = payRecordRepository.findByPayRecordsId(payRecordsId);
//        //List<Purchase> purchases = purchaseRepository.findByPayRecordsId(payRecordsId);
//
//        List<PurchaseDto> purchaseDtos = payRecords.stream()
//                .map(purchase -> PurchaseDto.builder()
//                        .id(purchase.getId())
//                        .payRecord(purchase)
//                        .build())
//                .collect(Collectors.toList());
//
//        return purchaseDtos;
//    }

//    return purchases.stream()
//            .map(purchase -> PurchaseDto.builder()
//            .id(purchase.getId())
//            .payRecord(purchase.getPayRecord())
//            .build())
//            .collect(Collectors.toList());

//    public PurchaseDto getPurchase(Long payRecordId) {
//        Optional<Purchase> purchase = purchaseRepository.findByPayRecordId(payRecordId);
//
//        return PurchaseDto.builder()
//                .id(purchase.get().getId())
//                .payRecord(purchase.get().getPayRecord())
//                .build();
//    }
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



>>>>>>> 56e99f55160264eece4c7e7aafbd1b82fd982711
