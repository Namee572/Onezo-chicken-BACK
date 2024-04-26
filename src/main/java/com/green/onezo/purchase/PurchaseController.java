package com.green.onezo.purchase;

import com.green.onezo.payRecord.PayRecord;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@Tag(name = "Purchase-Controller", description = "주문")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("")
    public Optional<Purchase> getOrder(@RequestParam Long id) {
        System.out.println("일로오나");
        System.out.println(id);
        return purchaseService.getOrder(id);

    }


}
