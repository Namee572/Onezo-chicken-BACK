package com.green.onezo.agreement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgreementService {
    private final AgreementRepository agreementRepository;

    public void agreeToAgreement() {
        Agreement agreement = new Agreement();
        agreement.setAgreed(true); // 클라이언트에서 동의 버튼을 눌렀을 때 true로 설정
        agreementRepository.save(agreement);
    }


}