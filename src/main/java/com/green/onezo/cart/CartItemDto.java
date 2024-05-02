package com.green.onezo.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


public class CartItemDto {

    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemReq {
        @NotNull(message = "멤버아이디는 필수입니다.")
        private Long memberId;
        @NotNull(message = "스토어아이디는 필수입니다.")
        private Long storeId;
        @NotNull(message = "메뉴아이디는 필수입니다.")
        private Long menuId;
        @NotNull(message = "수량은 필수입니다.")
        @Min(value = 1, message = "수량은 최소 1 이상이어야 합니다.")
        private int quantity;

    }

    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemRes {
        private Long cartItemId;
        private String storeName;
        private String menuName;
        private int quantity;
        private int price;

    }

}
