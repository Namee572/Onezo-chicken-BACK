package com.green.onezo.store;

import lombok.*;


import java.time.LocalTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    private Long id;

    private String storeName;
    private String address;
    private String addressOld;
    private String storePhone;
    private String openClose;
    private TakeOut takeOut;

    public StoreDto(Object[] data) {
        this.storeName = (String) data[0];
        this.address = (String) data[1];
        this.addressOld = (String) data[2];
    }
}
