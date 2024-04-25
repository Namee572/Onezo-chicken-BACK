package com.green.onezo.menu;

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

    private String stock;

    private String menuName;

    private double price;

    private String origin; // 원산지

    @Enumerated(EnumType.STRING)
    private MenuCategory menuCategory;

//    @OneToMany(mappedBy = "menu")
//    private List<CartItem> cart_items = new ArrayList<>();








}
