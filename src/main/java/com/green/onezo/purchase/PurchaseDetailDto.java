package com.green.onezo.purchase;

import com.green.onezo.member.Member;
import com.green.onezo.menu.Menu;
import jakarta.validation.constraints.NotBlank;

public class PurchaseDetailDto {
    @NotBlank
    private Menu menu;
    @NotBlank
    private Purchase purchase;
    @NotBlank
    private int quantity;

}
