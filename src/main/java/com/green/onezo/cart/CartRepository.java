package com.green.onezo.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    Optional<Cart> findByMemberId(Long memberId);

    List<Cart> findByMember(Member member);

    Optional<Cart> findByMember_UserId(String userId);

}
