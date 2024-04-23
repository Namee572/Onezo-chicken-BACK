package com.green.onezo.pay;


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
    private String customerEmail;
    private String customerName;

    public Pay toEntity(){
        return Pay.builder()
                .orderId(UUID.randomUUID().toString())
                .payType(payType)
                .amount(amount)
                .customerEmail(customerEmail)
                .customerName(customerName)
                .paySuccessYn("Y")
                .createDate(LocalDateTime.now())
                .build();
    }


}
