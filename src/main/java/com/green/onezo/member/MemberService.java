package com.green.onezo.member;

import com.green.onezo.enum_column.Resign_yn;
import com.green.onezo.global.error.BizException;
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

       Optional<Member> dbmember = memberRepository.findByUserId(memberDto.getUserId());
       if(dbmember.isPresent()){
           throw new BizException("아이디 중복입니다.");
       }
        Member member = new Member();
        member.setUserId(memberDto.getUserId());
        member.setPassword(memberDto.getPassword());//이거 암호화 진행 시켜야됨 jasypt
        member.setNickname(memberDto.getNickname());
        member.setName(memberDto.getName());
        member.setResign_yn(memberDto.getResign_yn());
        member.setPhone(memberDto.getPhone());
        //유저 권한을 부여.
        member.setRole(Role.USER);

        return memberRepository.save(member);
    }


    public Optional<Member> authenticate(String userId, String password) {
        return memberRepository.findByUserIdAndPassword(userId, password);
    }


    // 회원정보수정
    public void memberUpdate(Long memberId, MemberUpdateDto updateDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        if (updateDto.getPassword() != null && !updateDto.getPassword().isEmpty()) {
            member.setPassword(updateDto.getPassword());
        }
        if (updateDto.getName() != null && !updateDto.getName().isEmpty()) {
            member.setName(updateDto.getName());
        }

        if (updateDto.getNickname() != null && !updateDto.getNickname().isEmpty()) {
            member.setNickname(updateDto.getNickname());
        }

        if (updateDto.getPhone() != null && !updateDto.getPhone().isEmpty()) {
            member.setPhone(updateDto.getPhone());
        }

        memberRepository.save(member);
    }


    // 회원탈퇴
    public void memberResign(Long memberId, MemberResignDto resignDto) throws Exception {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        
        if (!member.getUserId().equals(resignDto.getUserId()) ||
                !member.getPassword().equals(resignDto.getPassword()) ||
                !member.getPhone().equals(resignDto.getPhone())) {
            throw new IllegalArgumentException("제공된 사용자 정보가 일치하지 않습니다.");
        }

        member.setResign_yn(Resign_yn.Y);
        memberRepository.save(member); // 변경된 탈퇴 상태를 데이터베이스에 저장
    }

    //아이디 찾기 , 비밀번호 찾기
}

