package com.green.onezo.menu;

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

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private double price;

    @Enumerated(EnumType.STRING)
    private MenuCategory menuCategory;

    @ManyToMany(mappedBy = "menus")
    private List<Store> stores = new ArrayList<>();

    @OneToOne(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private MenuInfo menuInfo;

    @OneToOne(mappedBy = "nutrient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Nutrient nutrient;


}
