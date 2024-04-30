package com.green.onezo.cart;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Tag(name = "cart-controller", description = "장바구니")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    @Operation(summary = "장바구니 아이템 추가", description = "요청데이터: 회원아이디, 수량, 매장아이디, 메뉴아이디",
            responses = {
                    @ApiResponse(responseCode = "200", description = "장바구니 추가 성공"),
                    @ApiResponse(responseCode = "400", description = "재고가 부족합니다."),
                    @ApiResponse(responseCode = "404", description = "메뉴 또는 스토어를 찾을 수 없음")
            })

    public ResponseEntity<String> addCart(@RequestBody @Valid CartDto cartDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            cartService.addCart(cartDto);
            return ResponseEntity.ok("장바구니에 추가되었습니다.");
        }
//        catch (OutOfStockException e) {
//            return ResponseEntity.badRequest().body("재고가 부족합니다.");
//        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("메뉴 또는 스토어를 찾을 수 없음");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("장바구니 추가 중 오류가 발생했습니다.");
        }
    }


    @GetMapping("/cartItem/{memberId}")
    @Operation(summary = "장바구니 조회", description = "특정 회원의 전체 장바구니 내역을 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "조회 성공"),
                    @ApiResponse(responseCode = "404", description = "회원 ID가 유효하지 않습니다."),
                    @ApiResponse(responseCode = "500", description = "장바구니 조회 중 오류가 발생했습니다.")
            })
    public ResponseEntity<?> getCart(@PathVariable @Valid Long memberId) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            try  {
                CartDto cartDto = cartService.getCart(memberId);
            return ResponseEntity.ok(cartDto);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원 ID가 유효하지 않습니다.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("장바구니 조회 중 오류가 발생했습니다.");
            }
        }


    @DeleteMapping("/cartItem/{memberId}/{cartItemId}")
    @Operation(summary = "장바구니 메뉴 삭제", description = "특정 회원의 장바구니에서 특정 메뉴를 삭제합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "삭제 성공"),
                    @ApiResponse(responseCode = "404", description = "회원 ID 또는 아이템 ID가 유효하지 않습니다."),
                    @ApiResponse(responseCode = "500", description = "장바구니 아이템 삭제 중 오류가 발생했습니다.")
            })
    public ResponseEntity<String> removeCartItem(@PathVariable @Valid Long memberId, @PathVariable @Valid Long cartItemId) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            cartService.deleteCartItem(memberId, cartItemId);
            return ResponseEntity.ok("장바구니 아이템이 삭제되었습니다.");
    }


    @PutMapping("/cartItem/{memberId}/{cartItemId}")
    @Operation(summary = "장바구니 수량 변경",
            description = "특정 메뉴의 수량을 변경합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "수량이 성공적으로 업데이트 되었습니다."),
                    @ApiResponse(responseCode = "400", description = "부적절한 수량 값이 제공되었습니다."),
                    @ApiResponse(responseCode = "404", description = "회원 ID 또는 아이템 ID가 존재하지 않습니다."),
                    @ApiResponse(responseCode = "500", description = "수량 변경 중 서버 오류가 발생했습니다.")
            })
    public ResponseEntity<String> updateCartItemQuantity(@PathVariable @Valid Long memberId,
                                                         @PathVariable @Valid Long cartItemId,
                                                         @RequestBody @Valid int quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            cartService.updateCartItemQuantity(memberId, cartItemId, quantity);
            return ResponseEntity.ok("장바구니 아이템 수량이 업데이트 되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("부적절한 수량 값이 제공되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원 ID 또는 아이템 ID가 존재하지 않습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수량 변경 중 서버 오류가 발생했습니다.");
        }
    }
}



