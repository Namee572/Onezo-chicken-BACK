package com.green.onezo.purchase;

import com.green.onezo.pay.PayRepository;
import com.green.onezo.payRecord.PayRecord;
import com.green.onezo.payRecord.PayRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PayRepository payRepository;
    private final PurchaseRepository purchaseRepository;
    private final PayRecordRepository payRecordRepository;

    @Transactional
    public Optional<Purchase> getOrder(Long id){
        return purchaseRepository.findByPayRecord_id(id);
    }



}