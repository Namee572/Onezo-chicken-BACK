package com.green.onezo.payRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayRecordRepository extends JpaRepository<PayRecord, Long> {

    Optional<PayRecord> findById(Long id);
}
