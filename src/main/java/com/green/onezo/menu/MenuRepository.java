package com.green.onezo.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
//    Optional<Menu> findByOriginAndNutrient(String origin, String nutrient);
//
//    Optional<MenuInfo> findByAll();
//
//  Menu findMenuById(Long id);

}

