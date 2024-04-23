package com.green.onezo.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {
    private Long id;
    private String storeName;
    private String address;
    private String storePhone;
    private LocalTime openTime;
    private LocalTime closeTime;
    private StoreStatus storeStatus;

}
