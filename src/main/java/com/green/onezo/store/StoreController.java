package com.green.onezo.store;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
