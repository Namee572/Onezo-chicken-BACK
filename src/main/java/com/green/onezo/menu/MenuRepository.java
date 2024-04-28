package com.green.onezo.menu;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByOriginAndNutrient(String origin, String nutrient);

    Optional<MenuInfo> findByAll();

    Menu findMenuById(Long id);

    // 재고 감소 메소드...
    @Modifying
    @Query("UPDATE Menu m SET m.stock = m.stock - :quantity WHERE m.id = :menuId AND m.stock >= :quantity")
    int decreaseStock(@Param("menuId") Long menuId, @Param("quantity") int quantity);


}

