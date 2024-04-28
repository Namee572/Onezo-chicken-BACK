package com.green.onezo.store;

import com.green.onezo.menu.Menu;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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

    private String addressOld;

    @Column(nullable = false)
    private String storePhone;

    @Column(nullable = false)
    private String storeHours;

    @Enumerated(EnumType.STRING)
    private TakeOut takeOut;

    @ManyToMany
    @JoinTable(
            name = "store_menu",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<Menu> menus = new ArrayList<>();
}
