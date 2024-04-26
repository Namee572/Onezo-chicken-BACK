package com.green.onezo.purchase;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PurchaseDto {


    private Long id;

    private String menu;
    private int quantity;
    private LocalDateTime payDate;
    //private Long orderId;


}
