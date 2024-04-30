package com.green.onezo.store;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findStoreById(Long id);

    List<TakeOut> findByOrderType(TakeOut orderType);

    @Query("SELECT s.storeName, s.address, s.addressOld FROM Store s")
    List<Object[]> findByStoreNameAndAddressAndAddressOld(@NotNull String storeName, @NotNull String address, @NotNull String addressOld);


}