package com.green.onezo.purchase;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.onezo.member.Member;
import com.green.onezo.menu.Menu;
import com.green.onezo.payRecord.PayRecord;
import com.green.onezo.store.Store;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Column(name = "purchase_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "pay_date", nullable = false)
    private LocalDateTime payDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payRecord_id")
    private PayRecord payRecord;


    @ManyToOne
    @JoinColumn(name = "pay_record_id")
    private PayRecord payRecord;


}
