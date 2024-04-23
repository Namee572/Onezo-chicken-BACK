package com.green.onezo.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepostitory extends JpaRepository<Store, Long> {
}
