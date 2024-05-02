package com.green.onezo.purchase;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PurchaseControllerTest {

    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    PurchaseDetailRepository purchaseDetailRepository;


    @ParameterizedTest
    @DisplayName("구매내역 조회")
    @ValueSource(longs = {1L})
    void getRecord(Long id) {
        Optional<Purchase> result = purchaseRepository.findById(id);
        if (result.isEmpty()) {
            System.out.println(result);
        }
        System.out.println(result);
    }
    @ParameterizedTest
    @ValueSource(longs = {1L})
    void getDetail(Long id) {
        Optional<PurchaseDetail> result = purchaseDetailRepository.findById(id);
        System.out.println(result);
    }
}