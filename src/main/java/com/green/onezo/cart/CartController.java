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




