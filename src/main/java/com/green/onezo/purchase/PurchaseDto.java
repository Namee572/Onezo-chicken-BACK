package com.green.onezo.purchase;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
public class PurchaseDto {

    private Long orderId;
    private LocalDateTime orderDate;
    private String orderType;
    private String menuName;
    //private String menuImage;



    private String menu;

    private int quantity;

    private LocalDateTime payDate;
}
