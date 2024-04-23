package com.green.onezo.store;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@RequiredArgsConstructor
//@NoArgsConstructor 기본생성자 선언
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    private String storeName;
    private String address;

    private String storePhone;
    private LocalTime openTime;
    private LocalTime closeTime;

    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;
    //private String imagePath;
}
