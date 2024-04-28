package com.green.onezo.purchase;

import jakarta.validation.constraints.NotBlank;


import com.green.onezo.pay.PayType;
import com.green.onezo.store.TakeOut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto {

    private Long orderId;
    private LocalDateTime orderDate;
    private String orderType;
    private String menuName;
    //private String menuImage;



    private String menu;


    private Long id;
    private String menuName;
    private int quantity;
    private LocalDateTime payDate;
    private String storeName;
    private PayType payType;
    private TakeOut takeOut;
    private double price;
    private Long amount; // 지불 금액
    private String orderId;
    //private Long orderId;

}
