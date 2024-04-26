package com.green.onezo.store;

import com.green.onezo.member.Member;
import com.green.onezo.menu.Menu;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Schema(description = "매장")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    private String storeName;
    private String address;
    private String storePhone;
    private String openClose;


}
