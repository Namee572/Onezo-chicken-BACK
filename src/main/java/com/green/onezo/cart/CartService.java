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

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final MenuRepository menuRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final CartItemRepository cartItemRepository;

    public void addCart(CartDto cartDto) {

        Menu menu = menuRepository.findById(cartDto.getMenuId()).orElseThrow(() -> new RuntimeException("주문 불가 메뉴."));
        Store store = storeRepository.findStoreById(cartDto.getStoreId());
        Member member = memberRepository.findById(cartDto.getMemberId()).orElseThrow();

        if (menu.getStock().equals("Out of stock")) {
            throw new RuntimeException("재고가 부족합니다.");
        }

        Optional<Cart> cart = cartRepository.findByMember_UserId(member.getUserId());

        if (cart.isPresent()) {
            Cart existingCart = cart.get();
            existingCart.getCartItemList().add(
                    CartItem.builder()
                            .quantity(cartDto.getQuantity())
                            .menu(menu)
                            .cart(existingCart)
                            .build()
            );
            cartRepository.save(existingCart);
        } else {
            Cart newcart = Cart.builder()
                    .member(member)
                    .store(store)
                    .build();

            if (newcart.getCartItemList() == null) {
                newcart.setCartItemList(new ArrayList<>());
            }
            newcart.getCartItemList().add(
                    CartItem.builder()
                            .quantity(cartDto.getQuantity())
                            .menu(menu)
                            .cart(newcart)
                            .build()
            );
            cartRepository.save(newcart);
        }
    }


    public CartDto getCart(Long memberId) {

        Cart cart = cartRepository.findByMemberId(memberId).orElseThrow(() -> new RuntimeException("장바구니가 존재하지 않습니다."));
        List<Long> menuIds = cart.getCartItemList().stream()
                .map(item -> item.getMenu().getId())
                .collect(Collectors.toList());
        List<Integer> quantities = cart.getCartItemList().stream()
                .map(CartItem::getQuantity)
                .collect(Collectors.toList());

        return new CartDto(cart.getId(), memberId, quantities.get(0), cart.getStore().getId(), menuIds.get(0));
    }

    public void deleteCartItem(Long memberId, Long cartItemId) {
        Cart cart = cartRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("장바구니가 존재하지 않습니다."));

        CartItem deleteItem = cart.getCartItemList().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("장바구니에 해당 아이템이 없습니다."));

        cart.getCartItemList().remove(deleteItem);
        cartItemRepository.delete(deleteItem);
    }

    public void updateCartItemQuantity(Long memberId, Long cartItemId, int newQuantity) {
        if (newQuantity <= 0) {
            deleteCartItem(memberId, cartItemId);
            return;
        }

        Cart cart = cartRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("장바구니가 존재하지 않습니다."));

        CartItem itemToUpdate = cart.getCartItemList().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("장바구니에 해당 아이템이 없습니다."));

        itemToUpdate.setQuantity(newQuantity);
        cartItemRepository.save(itemToUpdate);
    }


}