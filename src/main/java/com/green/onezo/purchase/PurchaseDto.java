package com.green.onezo.purchase;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PurchaseDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private String orderType;
    private String menuName;
    //private String menuImage;

}
