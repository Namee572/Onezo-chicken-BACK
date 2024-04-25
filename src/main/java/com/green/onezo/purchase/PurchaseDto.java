package com.green.onezo.purchase;


import com.green.onezo.cart.OrderType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
public class PurchaseDto {


    private Long orderId;

    private String menu;

    private int quantity;

    private LocalDateTime payDate;
}
