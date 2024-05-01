package com.green.onezo.purchase;

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
@Tag(name = "purchase-controller", description = "주문 내역")
public class PurchaseController {
    private final PurchaseService purchaseService;


    @GetMapping("/record")
    public Optional<Purchase> getRecord(@RequestParam Long id){
        return purchaseService.getPurchase(id);
    }

    @GetMapping("/detail")
    public Optional<PurchaseDetail> getDetail(@RequestParam Long id){
        return purchaseService.getDetail(id);
    }
}
