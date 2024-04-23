package com.green.onezo.menu;

import com.green.onezo.store.Store;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private String menuName;
    private String price;
    //private String imagePath;


    // 메뉴 상세정보들
    private int stock; // 재고
    private String origin; // 원산지
    private String allergyInfo; // 알레르기 정보
    private String nutrient; // 영양성분 정보
    @Enumerated(EnumType.STRING)
    private MenuCategory menuCategory;
    @Enumerated(EnumType.STRING)
    private MenuStatus menuStatus;

}
