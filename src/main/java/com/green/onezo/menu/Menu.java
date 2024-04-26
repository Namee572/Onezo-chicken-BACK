package com.green.onezo.menu;

import com.green.onezo.store.Store;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Schema(description = "메뉴")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;
    private String menuName;
    private String stock;
    private double price;

    @Enumerated(EnumType.STRING)
    private MenuCategory menuCategory;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "menuInfo_id")
    private MenuInfo menuInfo;

    @ManyToOne
    @JoinColumn(name = "nutrient_id")
    private Nutrient nutrient;



//    @OneToMany(mappedBy = "menu")
//    private List<CartItem> cart_items = new ArrayList<>();



}
