package com.green.onezo.purchase;

import com.green.onezo.pay.Pay;
import com.green.onezo.pay.PayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PayRepository payRepository;

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