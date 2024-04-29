package com.green.onezo.review;


import com.green.onezo.member.Member;
import com.green.onezo.purchase.Purchase;
import com.green.onezo.store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

    @ManyToOne
    @JoinColumn(name = "store_id") // 이는 DB의 실제 컬럼 이름
    private Store store;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private int star;

//    @CreationTimestamp
//    private LocalDateTime reviewRegDate;
//
//    @UpdateTimestamp
//    private LocalDateTime reviewUpDate;



}
