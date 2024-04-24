package com.green.onezo.cart;

import com.green.onezo.member.Member;
import com.green.onezo.member.MemberRepository;
import com.green.onezo.menu.Menu;
import com.green.onezo.menu.MenuRepository;
import com.green.onezo.store.Store;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MenuRepository menuRepository;


    // 장바구니에 아이템 추가
    @Transactional
    public void addCart(Member member, Store store, Menu menu, int quantity) {

        //  해당되는 메뉴가 없으면 Exception 처리
        Menu dbMenu = menuRepository.findById(menu.getId()).orElseThrow();
//        CartItem cartItem = CartItem.builder()
//                .member(member)
//                .quantity(quantity)
//                .store(store)
//                .menu(menu)
//                .build();


        Cart cart = Cart.builder()
                .member(member)
                .store(store)
                .menu(menu)
                .cartItemList(
                        Arrays.asList(
                        CartItem.builder()
                                .quantity(10)
                                .menu(dbMenu)
                            .build())
                        )
                .build();

        cartRepository.save(cart);
    }

    // 장바구니 아이템 조회
    public List<CartItem> getCartItems(Member member) {
        return cartRepository.findByMember(member);
    }

    // 장바구니 아이템 수량 조절
    public void updateQuantity(Long cartItemId, int quantity) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        optionalCartItem.ifPresent(cartItem -> {
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        });
    }

    // 장바구니 아이템 삭제
    public void deleteCartItem(Long cartItemId) {
        cartRepository.deleteById(cartItemId);
    }

    // 장바구니 총 결제 금액 계산
    public int totalPay(Member member) {
        List<CartItem> cartItems = cartRepository.findByMember(member);
        int totalPay = 0;
        for (CartItem cartItem : cartItems) {
            totalPay += cartItem.getMenu().getPrice() * cartItem.getQuantity();
        }
        return totalPay;
    }
}