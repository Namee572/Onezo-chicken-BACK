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

    //회원가입
    @Operation(summary = "회원 가입",
            description = "회원 가입")
    @PostMapping("signUp")
    public ResponseEntity<SignDto.Res> signup(
            @RequestBody @Valid SignDto.Req signDtoReq
    ) {
        memberService.signup(signDtoReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(SignDto.Res.builder()
                .message("회원가입이 완료되었습니다.")
                .build());
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
    public ResponseEntity<String> checkId(@RequestBody AuthCheckIdDto authCheckIdDto) {
        boolean checkIDDuplicate = memberRepository.existsByUserId(authCheckIdDto.getUserId());

        if (checkIDDuplicate) {
            return ResponseEntity.ok("중복된 아이디입니다");
        } else {
            return ResponseEntity.ok("사용가능한 아이디 입니다");
        }
    }

    //비밀번호 확인
    @Operation(summary = "비밀번호 확인",
                description = "비밀번호 확인과 비밀번호가 일치하는지 확인")
    @PostMapping("passwordCheck")
    public ResponseEntity<String> passwordCheck(@RequestBody MemberDto memberDto){
        String password=memberDto.getPassword();
        String passwordCheck= memberDto.getPasswordCheck();

        if(password.equals(passwordCheck)) {
            return ResponseEntity.ok("비밀번호가 일치합니다.");
        }else {
            return ResponseEntity.ok("비밀번호가 일치하지 않습니다.");
        }
    }


    @Operation(summary = "닉네임 중복체크",
            description = "입력한 닉네임을 db와 대조한뒤 중복 체크")
    @PostMapping("checkNickname")
    public ResponseEntity<CheckNickDto.Res> checkNick(@RequestBody CheckNickDto.Req checkNickDtoReq) {
        boolean checkNickDuplicate = memberRepository.existsByNickname(checkNickDtoReq.getNickname());

        if (checkNickDuplicate) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(CheckNickDto.Res.builder()
                            .message("중복된 검사입니다.")
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(CheckNickDto.Res.builder()
                    .message("사용가능한 닉네임입니다.")
                    .build());
        }
    }


    //로그인 기능
    @Operation(summary = "로그인 기능",
            description = "아이디 ,비밀번호를 DB와 대조해 회원이라면 로그인")
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
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
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
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            memberService.memberResign(memberId, resignDto);
            return ResponseEntity.ok("회원 탈퇴 성공.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 탈퇴 실패: " + e.getMessage());
        }
    }

}
