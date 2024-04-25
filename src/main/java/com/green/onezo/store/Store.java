package com.green.onezo.store;

import com.green.onezo.member.Member;
import com.green.onezo.menu.Menu;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;


@Entity
@Data
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    private String storeName;
    private String address;
    private String storePhone;
    private String openClose;


}
