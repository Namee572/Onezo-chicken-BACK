package com.green.onezo.menu;

import com.green.onezo.store.Store;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuStockInfo {
    private Menu menu;
    private Store store;
    private int stock;
}
