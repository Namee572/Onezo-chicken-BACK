package com.green.onezo.cart;

import com.green.onezo.member.Member;
import com.green.onezo.menu.Menu;
import com.green.onezo.store.Store;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Tag(name = "Cart-Controller", description = "장바구니")
public class CartController {

    private final CartService cartService;

    // 장바구니에 아이템 추가
    @PostMapping("/add")
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

//    // 장바구니 조회
//    @GetMapping("/{memberId}")
//    public List<CartItem> getAllCartItems(@PathVariable Long memberId){
//        List<CartItem> cartItems = cartService.getAllCartItems(memberId);
//        return cartItems;
//    }

    // 장바구니 전체 아이템 삭제
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return new ResponseEntity<>("장바구니 초기화 성공", HttpStatus.OK);
    }
}



