package com.green.onezo.cart;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long id;

    @NotNull(message = "수량을 입력해주세요.")
    @Min(value = 1, message = "수량은 최소 1개 이상이어야 합니다.")
    private int quantity;

    @NotNull(message = "매장 ID를 입력해주세요.")
    private Long storeId;

    @NotNull(message = "회원 ID를 입력해주세요.")
    private Long memberId;

    @NotNull(message = "메뉴 ID를 입력해주세요.")
    private Long menuId;

}
