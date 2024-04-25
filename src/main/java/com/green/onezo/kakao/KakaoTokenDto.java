package com.green.onezo.kakao;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class KakaoTokenDto {

    @Getter
    @Builder
    @ToString
    public static class Request {
        private String grant_type;
        private String client_id;
        private String redirect_url;
        private String code;

    }


}
