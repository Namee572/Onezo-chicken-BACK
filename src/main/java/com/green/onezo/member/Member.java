package com.green.onezo.member;

import com.green.onezo.pay.Pay;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "member")
    private List<Pay> pays = new ArrayList<>();
    public void addPay(Pay pay){
        pays.add(pay);
        pay.setMember(this);
    }


}
