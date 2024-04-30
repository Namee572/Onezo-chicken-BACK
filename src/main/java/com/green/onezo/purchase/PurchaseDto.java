package com.green.onezo.purchase;

import jakarta.validation.constraints.NotBlank;


import com.green.onezo.pay.PayType;
import com.green.onezo.payRecord.PayRecord;
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

    private PayRecord payRecord;

}
