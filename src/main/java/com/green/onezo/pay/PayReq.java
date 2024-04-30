package com.green.onezo.pay;


import com.green.onezo.cart.Cart;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayReq {

    private PayType payType;
    private Long amount;
    private String userId;
    private CustomerName customerName;

    public Pay toEntity(){
        return Pay.builder()
                .orderId(UUID.randomUUID().toString())
                .payType(payType)
                .amount(amount)
                .userId(userId)
                .customerName(customerName)
                .paySuccessYn("Y")
                .createDate(LocalDateTime.now())
                .build();
    }


}
