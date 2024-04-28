package com.green.onezo.member;

import com.green.onezo.enum_column.Resign_yn;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.green.onezo.enum_column.Role;
import jakarta.transaction.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
//    private PasswordEncoder passwordEncoder;
    //회원가입 signUp
    @Transactional
    public Member signup(MemberDto memberDto) {
        Member member = new Member();
        member.setUserId(memberDto.getUserId());
        member.setPassword(memberDto.getPassword());//이거 암호화 진행 시켜야됨 jasypt 개놈새키
        member.setNickname(memberDto.getNickname());
        member.setName(memberDto.getName());
        member.setResign_yn(memberDto.getResign_yn());
        member.setPhone(memberDto.getPhone());
        //유저 권한을 부여.
        member.setRole(Role.USER);

        return memberRepository.save(member);
    }


    public Optional<Member> authenticate(String userId, String password) {
        return memberRepository.findByUserIdAndPassword(userId,password);
    }


    // 회원정보수정
    public Member memberUpdate(MemberUpdateDto updateDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserId = authentication.getName();

        Member loggedMember = memberRepository.findByUserId(loggedUserId)
                .orElseThrow(() -> new IllegalArgumentException("로그인된 사용자 정보를 찾을 수 없습니다."));

        if(updateDto.getPassword() != null && !updateDto.getPassword().isEmpty()) {
            if (!updateDto.getPassword().equals(updateDto.getCheckPassword())) {
                throw new IllegalArgumentException("입력한 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            }
            loggedMember.setPassword(updateDto.getPassword());
        }
        if (updateDto.getName() != null) {
            loggedMember.setName(updateDto.getName());
        }
        if (updateDto.getNickname() != null) {
            loggedMember.setNickname(updateDto.getNickname());
        }
        if (updateDto.getPhone() != null) {
            loggedMember.setPhone(updateDto.getPhone());
        }

        // 업데이트된 정보를 저장
        return memberRepository.save(loggedMember);
    }

    // 회원탈퇴
    public Member memberResign(MemberResignDto resignDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserId = authentication.getName();

        Member member = memberRepository.findByUserId(loggedUserId)
                .orElseThrow(() -> new UsernameNotFoundException("로그인된 사용자를 찾을 수 없습니다."));

        // 입력된 정보 검증
        if (!member.getUserId().equals(resignDto.getUserId()) || !member.getPassword().equals(resignDto.getPassword()) ||
                !member.getPhone().equals(resignDto.getPhone())) {
            throw new IllegalArgumentException("제공된 사용자 정보가 일치하지 않습니다.");
        }

        // resign 컬럼을 'y'로 설정
        member.setResign_yn(Resign_yn.Y);
        return memberRepository.save(member);
    }

}

