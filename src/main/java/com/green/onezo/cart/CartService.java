package com.green.onezo.cart;

import com.green.onezo.member.Member;
import com.green.onezo.menu.Menu;
import com.green.onezo.menu.MenuRepository;
import com.green.onezo.store.Store;
import com.green.onezo.store.StoreRepostitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

//    public void addCart(Member member, Store store, Menu menu, int quantity, TakeInOut takeInOut) {
//        // 재고 확인
//        if (!checkStock(menu, quantity)) {
//            throw new RuntimeException("InsufficientStockException" + menu.getId());
//        }
//
//        // 장바구니에 있는 메뉴인지 확인
//
//        Cart cartItem = new Cart();
//        cartItem.setMember(member);
//        cartItem.setStore(store);
//        cartItem.setMenu(menu);
//        cartItem.setQuantity(quantity);
//        cartItem.setTakeInOut(takeInOut);
//        cartRepository.save(cartItem);
//    }
//
//    public void updateCartItem(Long cartId, int quantity) {
//        Optional<Cart> optionalCartItem = cartRepository.findById(cartId);
//        optionalCartItem.ifPresent(cartItem -> {
//            cartItem.setQuantity(quantity);
//            cartRepository.save(cartItem);
//        });
//    }
//
//    public void deleteCartItem(Long cartId) {
//        cartRepository.deleteById(cartId);
//    }
//
//    // 재고 확인
//    private boolean checkStock(Menu menu, int requestedQuantity) {
//        return menu.getStock() >= requestedQuantity;
//
//    }

}


//    // 매장부터 선택
//    public void StoreFirst(Member memberId, Store storeId, Menu menuId, int quantity) {
//        addCart(memberId, storeId, menuId, quantity);
//    }
//
//    // 매장 추후 선택
//    public void CartFirst(Member memberId, Menu menuId, int quantity) {
//        addCart(memberId, null, menuId, quantity);
//    }
//
//    // 매장 선택 후 storeId를 추가로 지정
//    public void selectStore(Long cartId, Store storeId) {
//        Optional<Cart> cartOptional = cartRepository.findById(cartId);
//        if (cartOptional.isPresent()) {
//            Cart cart = cartOptional.get();
//            cart.setStore(storeId);
//            cartRepository.save(cart);
//        } else {
//            throw new RuntimeException("지피티는 CartItemNotFoundException 쓰라는데 흠...");
//        }
//    }