package com.green.onezo.purchase;

import com.green.onezo.payRecord.PayRecord;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
@Tag(name = "Purchase-Controller", description = "주문 내역")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("/history/{memberId}")
    public ResponseEntity<List<PurchaseDto>> getPurchaseHistory(@PathVariable Long memberId) {
        List<PurchaseDto> purchaseHistory = purchaseService.getPurchaseHistory(memberId);
        return ResponseEntity.ok(purchaseHistory);
    }

    // 주문 상세 조회
    @GetMapping("/details/{purchaseId}")
    public ResponseEntity<PurchaseDto> getPurchaseDetails(@PathVariable Long purchaseId) {
        PurchaseDto purchaseDetails = purchaseService.getPurchaseDetails(purchaseId);
        return ResponseEntity.ok(purchaseDetails);
    }





}
