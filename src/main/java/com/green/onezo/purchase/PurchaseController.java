package com.green.onezo.purchase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
@Tag(name = "purchase-controller", description = "메인페이지 (주문 조회/ 주문 상세 조회)")
public class PurchaseController {
    private final PurchaseService purchaseService;


    @GetMapping("/record/{purchaseId}")
    public ResponseEntity<Optional<Purchase>> getRecord(@PathVariable Long purchaseId, @RequestBody PurchaseDto purchaseDto){
        Optional<Purchase> result = purchaseService.getPurchase(purchaseId);
        if (result.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Optional<PurchaseDetail>> getDetail(@PathVariable Long id, @RequestBody PurchaseDetailDto purchaseDetailDto){
        Optional<PurchaseDetail> result = purchaseService.getDetail(id);
        if (result.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }
}
