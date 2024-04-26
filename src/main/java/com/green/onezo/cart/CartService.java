package com.green.onezo.cart;

import com.green.onezo.member.Member;
import com.green.onezo.member.MemberRepository;
import com.green.onezo.menu.Menu;
import com.green.onezo.menu.MenuRepository;
import com.green.onezo.store.Store;
import com.green.onezo.store.StoreRepostitory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final MenuRepository menuRepository;
    private final MemberRepository memberRepository;
    private final StoreRepostitory storeRepostitory;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public void addCart(CartDto cartDto) {

        Menu menu = menuRepository.findMenuById(cartDto.getMenu_id());
        Store store = storeRepostitory.findStoreById(cartDto.getStore_id());
        Member member = memberRepository.findById(cartDto.getMember_id()).orElseThrow();

        Optional<Cart> cart = cartRepository.findByMember_UserId(member.getUserId());
        int quantity = cartDto.getQuantity();

        if(cart.isPresent()){
            Cart existingCart =cart.get();
            existingCart.getCartItemList().add(
                    CartItem.builder()
                            .quantity(cartDto.getQuantity())
                            .menu(menu)
                            .cart(existingCart)
                            .build()
            );

            cartRepository.save(existingCart);
        }else{
            Cart newcart = Cart.builder()
                    .member(member)
                    .store(store)
                    .build();

            if( newcart.getCartItemList()== null){
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

//    // 카트 아이템 조회
  

//    public List<CartItem> getAllCartItems(Long cartId) {
//        Optional<Cart> cartOptional = cartRepository.findById(cartId);
//        return cartRepository.findById(cartId);
//    }

    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }


}