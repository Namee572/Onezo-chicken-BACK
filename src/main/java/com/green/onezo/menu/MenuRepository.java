package com.green.onezo.menu;

import com.green.onezo.member.Member;
import com.green.onezo.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
   // int findStockByStoreAndMenu(Store store, Menu menu);
}
