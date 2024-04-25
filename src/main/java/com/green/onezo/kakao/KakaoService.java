package com.green.onezo.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final KakaoTokenClient kakaoTokenClient;
    private String contentType = "Content-Type: application/x-www-form-urlencoded";
    @Value("ee6034fee6eaca2e42ce04b136729cf7")
    private String clientId;
    @Value("i25c2321Aa5A9Jy1r6AgrOj2SihPO0Jn")
    private String clientSecret;
    @Value("http://loaclhost:8080/oauth/kakao/callback")
    private String redirectUrl;

    public String getKakaoToken(){
        System.out.println("clientId:"+clientId);
//        KakaoTokenDto.Request kakaoTokenDtoRequest=
//                KakaoTokenDto.Request.builder()
//                        .grant_type("authorization_code")
//                        .build();

//        kakaoTokenClient.getKakaoToken(contentType,kakaoTokenDtoRequest);

        return "getKakaoToken";
    }
}
