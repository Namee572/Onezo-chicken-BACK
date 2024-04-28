package com.green.onezo.member;


import com.green.onezo.jwt.JwtTokenDto;
import com.green.onezo.jwt.JwtTokenManager;
import com.green.onezo.kakao.KakaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {
    private final JwtTokenManager jwtUtil;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final KakaoService kakaoService;


    //약관동의

    //회원가입
    @PostMapping("signUp")
    public ResponseEntity<String> signup(
            @RequestBody @Valid MemberDto memberDto
    ) {
        memberService.signup(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
    }
    //회원가입 true false로 전달되도록해야하나 ????
//    @PostMapping("signUp")
//    public ResponseEntity<Boolean> signup(
//            @RequestBody @Valid MemberDto memberDto
//    ) {
//        boolean success = memberService.signup(memberDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(success);
//    }

    //아이디 중복체크
    @Operation(summary = "아이디 중복체크",
            description = "입력한 아이디를 db와 대조한뒤 중복 체크")
    @PostMapping("checkId")
    public ResponseEntity<String> checkId(@RequestBody Member member) {
        boolean checkIDDuplicate = memberRepository.existsByUserId(member.getUserId());

        if (checkIDDuplicate) {
            return ResponseEntity.ok("중복된 아이디입니다");
        } else {
            return ResponseEntity.ok("사용가능한 아이디 입니다");
        }
    }


    //로그인 기능
    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody MemberDto memberDto) {
        String memberId = memberDto.getUserId();
        String password = memberDto.getPassword();

        Optional<Member> isAuthenticated = memberService.authenticate(memberId, password);
//        return ResponseEntity.of(isAuthenticated);
        if (isAuthenticated.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } else {
            JwtTokenDto jwtTokenDto = JwtTokenDto.builder()
                    .accessToken(jwtUtil.generateAccessToken(isAuthenticated.get()))
                    .refeshToken(jwtUtil.generateRefreshToken(isAuthenticated.get()))
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(
                    jwtTokenDto);
        }
    }

    //카카오 로그인
    @GetMapping("oauth/kakao/callback")
    public String kakaoCallback(String code) {
        System.out.println("code=" + code);
        return code;
    }

    @PutMapping("/update/{memberId}")
    @Operation(summary = "회원 정보 수정")
    public ResponseEntity<String> updateMember(@RequestBody @Valid MemberUpdateDto updateDto, @PathVariable Long memberId) {
        try {
            memberService.memberUpdate(memberId, updateDto);
            return ResponseEntity.ok("회원 정보가 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 정보 업데이트 중 문제가 발생했습니다: " + e.getMessage());
        }
    }

    @PutMapping("/resign/{memberId}")
    @Operation(summary = "회원 탈퇴")
    public ResponseEntity<String> resignMember(@RequestBody @Valid MemberResignDto resignDto, @PathVariable Long memberId) {
        try {
            memberService.memberResign(memberId, resignDto);
            return ResponseEntity.ok("회원 탈퇴 성공.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 탈퇴 실패: " + e.getMessage());
        }
    }


}