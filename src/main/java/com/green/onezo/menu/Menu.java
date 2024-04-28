package com.green.onezo.menu;


import com.green.onezo.store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    private String stock;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "store_id") // 이는 DB의 실제 컬럼 이름
    private Store store;

    @Enumerated(EnumType.STRING)
    private MenuCategory menuCategory;

//    @ManyToMany(mappedBy = "menus")
//    private List<Store> stores = new ArrayList<>();

//    @OneToOne(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
//    private MenuInfo menuInfo;
//
//    @OneToOne(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Nutrient nutrient;


}
