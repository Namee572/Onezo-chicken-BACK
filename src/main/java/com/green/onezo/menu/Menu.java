package com.green.onezo.menu;

import com.green.onezo.store.Store;

import jakarta.persistence.*;
import lombok.Data;
import net.sf.jsqlparser.statement.select.Fetch;

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

    @Enumerated(EnumType.STRING)
    private MenuCategory menuCategory;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

//    @OneToMany(mappedBy = "menu")
//    private List<CartItem> cart_items = new ArrayList<>();








}
