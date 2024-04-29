package com.green.onezo.agreement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agreement")
@RequiredArgsConstructor
public class AgreementController {

    private final AgreementService agreementService;

    @PostMapping("/agree")
    public ResponseEntity<String> agreeToAgreement(){
        agreementService.agreeToAgreement();
        return ResponseEntity.ok("약관에 동의하였습니다.");
    }

}
