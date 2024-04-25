package com.green.onezo.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "kakao", url="https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=861")
public interface TestClient {
    @GetMapping("")
    String getTest();
}
