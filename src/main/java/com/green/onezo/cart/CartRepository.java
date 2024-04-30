package com.green.onezo.cart;

import com.green.onezo.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    Optional<Cart> findByMember_UserId(String UserId);

    Optional<Cart> findByMemberId(Long memberId);

}
