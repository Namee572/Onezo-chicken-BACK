package com.green.onezo.menu;


import com.green.onezo.store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.sf.jsqlparser.statement.select.Fetch;
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

    @Enumerated(EnumType.STRING)
    private MenuCategory menuCategory;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

//    @OneToMany(mappedBy = "menu")
//    private List<CartItem> cart_items = new ArrayList<>();
    
    @Column(nullable = false)
    private double price;


//    @ManyToMany(mappedBy = "menus")
//    private List<Store> stores = new ArrayList<>();

//    @OneToOne(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
//    private MenuInfo menuInfo;
//
//    @OneToOne(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Nutrient nutrient;


}
