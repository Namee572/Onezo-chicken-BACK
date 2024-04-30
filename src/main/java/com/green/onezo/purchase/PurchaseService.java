package com.green.onezo.purchase;

import com.green.onezo.pay.Pay;
import com.green.onezo.pay.PayRepository;
import com.green.onezo.pay.PayType;
import com.green.onezo.payRecord.PayRecord;
import com.green.onezo.payRecord.PayRecordRepository;
import com.green.onezo.review.Review;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PayRecordRepository payRecordRepository;
}

//    public List<Purchase> purchaseList(Long payRecordId ) {
//        return purchaseRepository.findByPayRecordId(payRecordId);
//    }
//}


//    public List<PurchaseDto> getPurchases(Long payRecordsId) {
//        List<PayRecord> payRecords = payRecordRepository.findByPayRecordsId(payRecordsId);
//        //List<Purchase> purchases = purchaseRepository.findByPayRecordsId(payRecordsId);
//        List<PurchaseDto> purchaseDtos = payRecords.stream()
//                .map(purchase -> PurchaseDto.builder()
//                        .id(purchase.getId())
//                        .payRecord(purchase)
//                        .build())
//                .collect(Collectors.toList());
//        return purchaseDtos;
//    }
//    return purchases.stream()
//            .map(purchase -> PurchaseDto.builder()
//            .id(purchase.getId())
//            .payRecord(purchase.getPayRecord())
//            .build())
//            .collect(Collectors.toList());
//    public PurchaseDto getPurchase(Long payRecordId) {
//        Optional<Purchase> purchase = purchaseRepository.findByPayRecordId(payRecordId);
//        return PurchaseDto.builder()
//                .id(purchase.get().getId())
//                .payRecord(purchase.get().getPayRecord())
//                .build();
//    }
//}

