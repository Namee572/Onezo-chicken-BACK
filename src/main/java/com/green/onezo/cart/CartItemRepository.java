package com.green.onezo.cart;

import com.green.onezo.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

//    List<CartItem> findByMember(Member member);


}
