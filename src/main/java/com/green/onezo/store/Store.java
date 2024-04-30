package com.green.onezo.store;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String address;

    private String addressOld;

    @Column(nullable = false)
    private String storePhone;

    @Column(nullable = false)
    private String storeHours;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

}
