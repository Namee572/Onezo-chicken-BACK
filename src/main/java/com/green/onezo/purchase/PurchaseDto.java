package com.green.onezo.purchase;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PurchaseDto {


    private Long orderId;

    private String menu;

    private int quantity;

    private LocalDateTime payDate;
}
