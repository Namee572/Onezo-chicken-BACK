package com.green.onezo.purchase;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailRepository purchaseDetailRepository;

//    @Transactional
//    public Optional<Purchase> getPurchase(Long id) {
//        return purchaseRepository.findById(id);
//    }
    @Transactional
    public Optional<Purchase> getPurchase(Long purchaseId) {
        return purchaseRepository.findById(purchaseId);
    }

    @Transactional
    public Optional<PurchaseDetail> getDetail(Long id){
        return purchaseDetailRepository.findById(id);
    }

}
