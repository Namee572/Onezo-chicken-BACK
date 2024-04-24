package com.green.onezo.order;

import com.green.onezo.pay.Pay;
import com.green.onezo.pay.PayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final PayRepository payRepository;

    public List<OrderDto> getOrderHistory(String userId) {
        List<Pay> orders = payRepository.findTop5ByUserId(userId);
        return mapToDto(orders);

    }

    private List<OrderDto> mapToDto(List<Pay> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Pay pay : orders) {
            OrderDto orderDto = OrderDto.builder()
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