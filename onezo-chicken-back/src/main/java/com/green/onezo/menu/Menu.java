package com.green.onezo.menu;

import com.green.onezo.cart.CartItem;
import com.green.onezo.store.Store;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    private String menuName;

    private double price;

    //private String imagePath;

    private String origin; // 원산지

    private String allergyInfo; // 알레르기 정보

    private String nutrient; // 영양성분 정보

    @Enumerated(EnumType.STRING)
    private MenuCategory menuCategory;

    @Enumerated(EnumType.STRING)
    private MenuStatus menuStatus; // 메뉴 상태

    @OneToMany(mappedBy = "menu")
    private List<CartItem> cart_items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;



//    @ManyToOne
//    @JoinColumn(name = "store_id")
//    private Store store;


}