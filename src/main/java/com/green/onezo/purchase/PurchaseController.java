package com.green.onezo.purchase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
@Tag(name = "purchase-controller", description = "메인페이지 (주문 조회/ 주문 상세 조회)")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping("/record")
    @Operation(summary = "주문 내역 조회", description = "주어진 주문 ID에 해당하는 주문 기록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "주문 내역 조회 성공"),
            @ApiResponse(responseCode = "404", description = "주어진 ID로 주문 내역을 찾을 수 없습니다"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 발생")
    })
    public Optional<Purchase> getRecord(@RequestParam Long id){
        return purchaseService.getPurchase(id);
    }

    @GetMapping("/detail")
    @Operation(summary = "주문 상세 조회", description = "주어진 주문 상세 ID로 주문 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "주문 상세 조회 성공"),
            @ApiResponse(responseCode = "404", description = "주어진 ID로 주문 상세를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 발생")
    })
    public Optional<PurchaseDetail> getDetail(@RequestParam Long id){
        return purchaseService.getDetail(id);
    }
}
