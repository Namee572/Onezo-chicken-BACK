package com.green.onezo.order;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Tag(name = "purchase-controller", description = "주문 내역")
public class PurchaseController {
    private final PurchaseService orderService;

}
