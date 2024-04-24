package com.green.onezo.review;


import com.green.onezo.member.Member;
import com.green.onezo.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String review;
    private int star;
    @CreationTimestamp
    private LocalDateTime reviewRegDate;

    @UpdateTimestamp
    private LocalDateTime reviewUpDate;

    @Enumerated(EnumType.STRING)
    private Delete delete;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
