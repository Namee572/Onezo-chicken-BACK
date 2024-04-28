package com.green.onezo.purchase;

import com.green.onezo.payRecord.PayRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto {

    private PayRecord payRecord;


}
