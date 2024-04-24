package com.green.onezo.store;

import com.green.onezo.menu.Menu;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
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


    
}
