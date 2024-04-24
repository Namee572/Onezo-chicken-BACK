package com.green.onezo.order;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private String orderType;
    private String menuName;
    //private String menuImage;

}
