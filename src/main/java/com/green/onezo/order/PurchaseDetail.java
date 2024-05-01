package com.green.onezo.order;

import com.green.onezo.menu.Menu;
import jakarta.persistence.*;
import lombok.*;


@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseDetail {

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(nullable = false)
    private int quantity;


}