package com.green.onezo.member;

import lombok.RequiredArgsConstructor;
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
}
