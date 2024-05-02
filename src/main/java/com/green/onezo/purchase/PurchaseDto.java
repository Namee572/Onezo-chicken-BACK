package com.green.onezo.purchase;


import com.green.onezo.member.Member;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class PurchaseDto {

    @NotBlank
    private Long id;
    private Purchase purchase;
    @NotBlank
    private Member member;
    @NotBlank
    private LocalDateTime payDate;


}
