package com.green.onezo.purchase;

import com.green.onezo.payRecord.PayRecord;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
@Tag(name = "purchase-controller", description = "주문 내역")
public class PurchaseController {

    //private final PurchaseService purchaseService;

//    @GetMapping("/{payRecordId}")
//    @Operation(summary = "주문 내역 리스트")
//    public List<PurchaseDto> getPurchases(@PathVariable Long payRecordsId) {
//        return purchaseService.getPurchases(payRecordsId);
//    }
//    }

//    @GetMapping("/{payRecordId}/detail")
//    @Operation(summary = "주문 내역 상세")
//    public ResponseEntity<PurchaseDto> getPurchaseDetail(@PathVariable Long payRecordId) {
//        try {
//            PurchaseDto purchaseDto = purchaseService.getPurchase(payRecordId);
//            return ResponseEntity.ok(purchaseDto);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}

//    @GetMapping("/{id}")
//    public ResponseEntity<PurchaseDto> getPurchaseDetail(@PathVariable Long id) {
//        try {
//            PurchaseDto purchaseDto = purchaseService.getPurchaseById(id);
//            return ResponseEntity.ok(purchaseDto);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }


//    // 주문 상세 조회
//    @GetMapping("/details/{purchaseId}")
//    public ResponseEntity<PurchaseDto> getPurchaseDetails(@PathVariable Long purchaseId) {
//        PurchaseDto purchaseDetails = purchaseService.getPurchaseDetails(purchaseId);
//        return ResponseEntity.ok(purchaseDetails);
//    }


}

