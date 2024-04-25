package com.green.onezo.purchase;

import com.green.onezo.pay.PayRepository;
import com.green.onezo.payRecord.PayRecord;
import com.green.onezo.payRecord.PayRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PayRepository payRepository;
    private final PurchaseRepository purchaseRepository;
    private final PayRecordRepository payRecordRepository;

    @Transactional
    public Optional<Purchase> getOrder(Long id){
        return purchaseRepository.findByPayRecord_id(id);
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