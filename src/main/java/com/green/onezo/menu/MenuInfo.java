package com.green.onezo.menu;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuInfoId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private String allergy;
    private String origin;

}
