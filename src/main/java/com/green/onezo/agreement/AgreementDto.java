package com.green.onezo.agreement;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@Data
@ToString
public class AgreementDto {

    private boolean agreed;
}

