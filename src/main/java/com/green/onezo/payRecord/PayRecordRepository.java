package com.green.onezo.payRecord;

import com.green.onezo.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PayRecordRepository extends JpaRepository<PayRecord, Long> {

    //Optional<PayRecord> findById(Long id);
    //List<PayRecord> findByPayRecordsId(Long id);
}
