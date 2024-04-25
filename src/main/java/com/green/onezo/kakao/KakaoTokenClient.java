package com.green.onezo.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakao",url="http://kauth.kakao.com/oauth/token")
public interface KakaoTokenClient {

    @PostMapping(value = "",consumes = "application/json")
    String getKakaoToken(
            @RequestHeader("ContentType") String contentType,
            KakaoTokenDto.Request kakaoTokenDtoRequest);

}
