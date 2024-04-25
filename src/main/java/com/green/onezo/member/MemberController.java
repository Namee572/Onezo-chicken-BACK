package com.green.onezo.member;


import com.green.onezo.jwt.JwtTokenDto;
import com.green.onezo.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {
    private final JwtUtil jwtUtil;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    //회원가입
    @PostMapping("signUp")
    public ResponseEntity<MemberDto> signup(
            @RequestBody @Valid MemberDto memberDto
//            BindingResult bindingResult
    ) {
//        if(bindingResult.hasErrors()){
//            // memberDto 유효성이 안맞아서 hasError가 되면
//            // 프론트 쪽에다가 memberDTO
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(memberDTO);
//        }
        memberService.signup(memberDto);
        return ResponseEntity.ok(new MemberDto());
    }


    //로그인 기능
    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody MemberDto memberDto){
        String memberId=memberDto.getUserId();
        String password=memberDto.getPassword();

        Optional<Member> isAuthenticated = memberService.authenticate(memberId,password);
//        return ResponseEntity.of(isAuthenticated);
        if(isAuthenticated.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }else{
            JwtTokenDto jwtTokenDto =  JwtTokenDto.builder()
                    .accessToken(jwtUtil.generateAccessToken(isAuthenticated.get()))
                    .refeshToken(jwtUtil.generateRefreshToken(isAuthenticated.get()))
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(
                    jwtTokenDto);
        }
    }
}
