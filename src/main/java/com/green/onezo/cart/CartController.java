package com.green.onezo.cart;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Tag(name = "Cart-Controller", description = "장바구니")
public class CartController {
    private final CartService cartService;

    // 장바구니에 아이템 추가
    @PostMapping("/add/{memberId}/{storeId}/{menuId}")
    public ResponseEntity<String> addCart(@RequestBody CartDto cartDto) {

        // Jwt
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // userId -> memberId
        try {
            cartService.addCart(cartDto);
            return new ResponseEntity<>("메뉴 담기 성공", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("메뉴 담기 실패: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    // 장바구니에 아이템 추가 (매장 선택 O)
//    @PostMapping("/cart/add/store/{storeId}")
//    public ResponseEntity<String> addCart(Member memberId, Store storeId, Menu menuId,int            quantity) {
//        cartService.addCart(memberId,storeId,menuId,quantity);
//        return new ResponseEntity<>("메뉴 담기 성공", HttpStatus.OK);
//    }
//
//    // 장바구니에 아이템 추가(매장 선택 X)
//    @PostMapping("/cart/add")
//    public ResponseEntity<String> addCart(Member memberId, Menu menuId, int quantity) {
//        cartService.addCart(memberId,null,menuId,quantity);
//        return new ResponseEntity<>("메뉴 담기 성공", HttpStatus.OK);
//    }

//    장바구니에 아이템 추가 (매장 선택 O)
//    @PostMapping("/cart/add/store/{storeId}")
//    public ResponseEntity<String> addCart(Member memberId, Store storeId, Menu menuIdint.   
//    quantity) {
//    cartService.addCart(memberId,storeId,menuId,quantity);
//    return new ResponseEntity<>("메뉴 담기 성공", HttpStatus.OK);
//    }


//
//    // 장바구니 아이템 조회
//    @GetMapping("/cart/items")
//    public ResponseEntity<List<CartItem>> getCartItems(Member memberId) {
//        List<CartItem> cartItems = cartService.getCartItems(memberId);
//        return new ResponseEntity<>(cartItems,HttpStatus.OK);
//    }

//     장바구니 아이템 수량 조절
//    @PutMapping("/update/{cartItemId}")
//    public ResponseEntity<String> updateQuantity(@PathVariable Long cartItemId, int quantity)  
// {
//        cartService.updateQuantity(cartItemId, quantity);
//        return new ResponseEntity<>("수량 업뎃 성공", HttpStatus.OK);
//    }

//     장바구니 아이템 삭제
//    @DeleteMapping("/delete/{cartItemId}")
//    public ResponseEntity<String> deleteItem(@PathVariable Long cartItemId) {
//        cartService.deleteCartItem(cartItemId);
//        return new ResponseEntity<>("아이템 삭제 성공", HttpStatus.OK);
//    }
//
//    // 장바구니 총 결제 금액 조회
//    @GetMapping("/cart/total-pay")
//    public ResponseEntity<Integer> totalPay(Member memberId) {
//        int totalPay = cartService.totalPay(memberId);
//        return new ResponseEntity<>(totalPay,HttpStatus.OK);
//    }
 }
    @GetMapping("/cartItem/{memberId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long memberId) {
        CartDto cart = cartService.getCart(memberId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok().build();
    }
}




