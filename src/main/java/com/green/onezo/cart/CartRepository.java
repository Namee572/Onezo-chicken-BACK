package com.green.onezo.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    //Optional<Cart> findByUserId(String UserId);


    Optional<Cart> findByMemberId(Long memberId);

    //Cart findByUserId(String userId);
}
