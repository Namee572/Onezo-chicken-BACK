package com.green.onezo.cart;

import com.green.onezo.member.Member;
import com.green.onezo.member.MemberRepository;
import com.green.onezo.menu.Menu;
import com.green.onezo.menu.MenuRepository;
import com.green.onezo.store.Store;
import com.green.onezo.store.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final MenuRepository menuRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public void addCart(CartDto cartDto) {

        Menu menu = menuRepository.findById(cartDto.getMenuId()).orElseThrow(() -> new RuntimeException("주문 불가 메뉴."));
        Store store = storeRepository.findStoreById(cartDto.getStoreId());
        Member member = memberRepository.findById(cartDto.getMemberId()).orElseThrow();

        if (menu.getStock().equals("Out of stock")) {
            throw new RuntimeException("재고가 부족합니다.");
        }

        Optional<Cart> existingCart = cartRepository.findByMemberId(member.getId());

        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();
            CartItem newCartItem = CartItem.builder()
                    .quantity(cartDto.getQuantity())
                    .menu(menu)
                    .cart(cart)
                    .build();
            cart.getCartItems().add(newCartItem);
            cartRepository.save(cart);
        } else {
            Cart newCart = Cart.builder()
                    .member(member)
                    .store(store)
                    .cartItems(new ArrayList<>())
                    .build();

            CartItem newCartItem = CartItem.builder()
                    .quantity(cartDto.getQuantity())
                    .menu(menu)
                    .cart(newCart)
                    .build();
            newCart.getCartItems().add(newCartItem);
            cartRepository.save(newCart);
        }
    }


    public CartDto getCart(Long memberId) {

        Cart cart = cartRepository.findByMemberId(memberId).orElseThrow(() -> new RuntimeException("장바구니가 존재하지 않습니다."));
        List<Long> menuIds = cart.getCartItems().stream()
                .map(item -> item.getMenu().getId())
                .collect(Collectors.toList());
        List<Integer> quantities = cart.getCartItems().stream()
                .map(CartItem::getQuantity)
                .collect(Collectors.toList());

        return new CartDto(cart.getId(), quantities.get(0), memberId, cart.getStore().getId(), menuIds.get(0));
    }

    public void deleteCartItem(Long memberId, Long cartItemId) {
        Cart cart = cartRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("장바구니가 존재하지 않습니다."));

        CartItem deleteItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("장바구니에 해당 아이템이 없습니다."));

        cart.getCartItems().remove(deleteItem);
        cartItemRepository.delete(deleteItem);
    }

    public void updateQuantity(Long memberId, Long cartItemId, int newQuantity) {
        if (newQuantity <= 0) {
            deleteCartItem(memberId, cartItemId);
            return;
        }

        Cart cart = cartRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("장바구니가 존재하지 않습니다."));

        CartItem itemToUpdate = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("장바구니에 해당 아이템이 없습니다."));

        itemToUpdate.setQuantity(newQuantity);
        cartItemRepository.save(itemToUpdate);
    }

}