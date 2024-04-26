package com.green.onezo.purchase;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.onezo.member.Member;
import com.green.onezo.payRecord.PayRecord;
import com.green.onezo.store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private String menu;

    private int quantity;

    private LocalDateTime payDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payRecord_id")
    private PayRecord payRecord;






}
