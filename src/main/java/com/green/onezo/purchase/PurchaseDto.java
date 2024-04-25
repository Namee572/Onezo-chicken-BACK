package com.green.onezo.purchase;


import com.green.onezo.cart.OrderType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PurchaseDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private OrderType orderType;
    private String menuName;
    private int totalPrice;



}
