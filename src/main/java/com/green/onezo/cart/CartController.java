package com.green.onezo.cart;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Tag(name = "cart-controller", description = "장바구니")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    @Operation(summary = "장바구니 상품 추가")
    public ResponseEntity<String> addCart(
            @RequestBody CartDto cartDto,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION,required = false) String auth
    ) {
        System.out.println("auth = "+auth);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            cartService.addCart(cartDto);
            return new ResponseEntity<>("장바구니에 추가되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("장바구니에 추가 안되었습니다.", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/cartItem/{memberId}")
    @Operation(summary = "장바구니 조회")
    public ResponseEntity<?> getCart(@PathVariable Long memberId) {
        try {
            CartDto cartDto = cartService.getCart(memberId);
            return ResponseEntity.ok(cartDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/cartItem/{memberId}/{cartItemId}")
    @Operation(summary = "장바구니 아이템 삭제")
    public ResponseEntity<String> removeCartItem(@PathVariable Long memberId, @PathVariable Long cartItemId) {
        try {
            cartService.deleteCartItem(memberId, cartItemId);
            return ResponseEntity.ok("장바구니 아이템이 삭제되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/cartItem/{memberId}/{cartItemId}")
    @Operation(summary = "장바구니 수량 변경")
    public ResponseEntity<String> updateCartItemQuantity(@PathVariable Long memberId, @PathVariable Long cartItemId, @RequestBody int quantity) {
        try {
            cartService.updateCartItemQuantity(memberId, cartItemId, quantity);
            return ResponseEntity.ok("장바구니 아이템 수량이 업데이트 되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}




