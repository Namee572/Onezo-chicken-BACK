package com.green.onezo.cart;

import com.green.onezo.member.Member;
import com.green.onezo.menu.Menu;
import com.green.onezo.pay.Pay;
import com.green.onezo.store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private int quantity;

<<<<<<< HEAD
    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;
=======

>>>>>>> 6977126 (240425)

    @OneToMany(mappedBy = "cart")
    private List<Pay> pays = new ArrayList<>();

    public void addPay(Pay pay) {
        pays.add(pay);
        pay.setCart(this);
    }
}
