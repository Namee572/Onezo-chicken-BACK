package com.green.onezo.cart;

import com.green.onezo.member.Member;
import com.green.onezo.menu.Menu;
import com.green.onezo.pay.Pay;
import com.green.onezo.store.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="member_id")
    private Member member;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "cart")
    private List<Pay> pays = new ArrayList<>();

    public void addPay(Pay pay) {
        pays.add(pay);
        pay.setCart(this);
    }



//    public static Cart createCart(Member member) {
//        Cart cart = new Cart();
//        cart.setMember(member);
//        return cart;
//    }


}
