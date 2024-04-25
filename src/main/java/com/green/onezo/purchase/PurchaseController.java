package com.green.onezo.purchase;

import com.green.onezo.payRecord.PayRecord;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("")
    public Optional<Purchase> getOrder(@RequestParam Long id) {
        System.out.println("일로오나");
        System.out.println(id);
        return purchaseService.getOrder(id);

    }


}
