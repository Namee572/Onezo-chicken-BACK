package com.green.onezo.review;


import com.green.onezo.member.Member;
import com.green.onezo.purchase.Purchase;
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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String review;

    private int star;

    @CreationTimestamp
    private LocalDateTime reviewRegDate;

    @UpdateTimestamp
    private LocalDateTime reviewUpDate;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


}
