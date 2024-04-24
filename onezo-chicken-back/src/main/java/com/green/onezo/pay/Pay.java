package com.green.onezo.pay;


import com.green.onezo.cart.Cart;
import com.green.onezo.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Long id;

    @Column(nullable = false)
    private Long amount;
    @Enumerated(EnumType.STRING)
    private PayType payType;
    @Column(nullable = false)
    private String orderId;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private LocalDateTime createDate;
    @Column(nullable = false)
    private String paySuccessYn;

    @Column
    @Setter
    private String paymentKey;

    @Column
    private String payFailReason;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    public PayRes toDto(){
        return PayRes.builder()
                .payType(payType.name())
                .amount(amount)
                .orderId(orderId)
                .userId(userId)
                .customerName(customerName)
                .createDate(LocalDateTime.now())
                .build();
    }


}