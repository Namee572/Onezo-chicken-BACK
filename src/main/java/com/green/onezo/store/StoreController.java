package com.green.onezo.store;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
@Tag(name = "Store-Controller", description = "매장")
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/store/{storeId}/detail")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable Long store) {
        StoreDto storeDto = storeService.getStoreById(store);
        return new ResponseEntity<>(storeDto, HttpStatus.OK);
    }

    @GetMapping("/address")
    public ResponseEntity<List<StoreDto>>getStoreAddress(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String addressOld) {
        List<StoreDto> stores = storeService.findByStoreNameAndAddressAndAddressOld(storeName, address, addressOld);
        return ResponseEntity.ok(stores);
    }

}
