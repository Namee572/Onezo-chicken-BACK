package com.green.onezo.store;

import lombok.*;

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
    private String storeHours;
    private OrderType orderType;

}
