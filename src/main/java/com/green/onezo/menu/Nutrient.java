package com.green.onezo.menu;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Nutrient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nutrient_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private Float kcal;
    private Float na;
    private Float sugars;
    private Float fat;
    private Float protein;

    @OneToOne
    @JoinColumn(name = "menu_id", unique = true)
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(nullable = false)
    private int kcal;

    @Column(nullable = false)
    private int na;

    @Column(nullable = false)
    private int carb;

    @Column(nullable = false)
    private int sugar;

    @Column(nullable = false)
    private int fat;

    @Column(nullable = false)
    private int protein;
}
