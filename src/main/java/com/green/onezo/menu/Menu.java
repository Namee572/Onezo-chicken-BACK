package com.green.onezo.menu;


import com.green.onezo.cart.CartItem;
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


    @Column(nullable = false)
    private double price;

}
