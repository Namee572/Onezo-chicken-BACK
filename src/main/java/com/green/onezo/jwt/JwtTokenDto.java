package com.green.onezo.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtTokenDto {

    private String accessToken;
    private String refeshToken;
}
