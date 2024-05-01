package com.green.onezo.purchase;

import com.green.onezo.purchase.Purchase;
import com.green.onezo.purchase.PurchaseDetail;
import com.green.onezo.purchase.PurchaseDetailRepository;
import com.green.onezo.purchase.PurchaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailRepository purchaseDetailRepository;

    @Transactional
    public Optional<Purchase> getPurchase(Long id) {
        return purchaseRepository.findById(id);
    }

    @Transactional
    public Optional<PurchaseDetail> getDetail(Long id){
        return purchaseDetailRepository.findById(id);
    }

}
