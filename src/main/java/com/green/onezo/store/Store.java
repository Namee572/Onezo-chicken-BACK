package com.green.onezo.store;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    private String storeName;
    private String address;

    // 매장 상세정보들
    private String storePhone;
    private LocalTime openTime;
    private LocalTime closeTime;
    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;
    //private String imagePath;
}
