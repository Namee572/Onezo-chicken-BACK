package com.green.onezo.cart;

import com.green.onezo.member.Member;
import com.green.onezo.menu.Menu;
import com.green.onezo.store.Store;
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

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "수량은 필수입니다.")
    @Min(value = 1, message = "수량은 최소 1개 이상이어야 합니다.")
    private int quantity;

    @NotNull(message = "매장 ID는 필수입니다.")
    private Long storeId;

    @NotNull(message = "메뉴 ID는 필수입니다.")
    private Long menuId;

}
