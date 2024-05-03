package com.green.onezo.member;

import com.green.onezo.enum_column.ResignYn;
import com.green.onezo.global.error.BizException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberControllerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원 정보 업데이트")
    void updateMember() {
        // 준비: 회원 생성 및 저장
        Member newMember = new Member();
        newMember.setUserId("user123@");
        newMember.setPassword("password");
        newMember.setPhone("010-1234-5678");
        newMember.setNickname("nickname");
        newMember.setName("Test User");
        memberRepository.save(newMember);

        // 실행: 회원 정보 업데이트
        MemberUpdateDto.UpdateReq updateReq = new MemberUpdateDto.UpdateReq();
        updateReq.setPassword("newPassword");
        updateReq.setPasswordCheck("newPassword");
        updateReq.setName("Updated Name");
        updateReq.setNickname("UpdatedNickname");
        updateReq.setPhone("010-8765-4321");
        try {
            memberService.memberUpdate(newMember.getId(), updateReq);
        } catch (BizException e) {
            fail(e.getMessage());
        }

        // 검증: 업데이트된 정보 확인
        Member updatedMember = memberRepository.findById(newMember.getId()).orElseThrow();
        assertEquals("Updated Name", updatedMember.getName());
        assertEquals("UpdatedNickname", updatedMember.getNickname());
        assertEquals("010-8765-4321", updatedMember.getPhone());
    }
    @Test
    @DisplayName("회원 탈퇴")
    void resignMember() {
        // 준비: 회원 생성 및 저장
        Member newMember = new Member();
        newMember.setUserId("user2");
        newMember.setPassword("password");
        newMember.setPhone("010-0000-0000");
        memberRepository.save(newMember);

        // 실행: 회원 탈퇴
        MemberResignDto.ResignReq resignReq = new MemberResignDto.ResignReq();
        resignReq.setUserId("user2");
        resignReq.setPassword("password");
        resignReq.setPhone("010-0000-0000");
        try {
            memberService.memberResign(newMember.getId(), resignReq);
        } catch (BizException e) {
            fail(e.getMessage());
        }

        // 검증: 회원 탈퇴 상태 확인
        Member resignedMember = memberRepository.findById(newMember.getId()).orElseThrow();
        assertEquals(ResignYn.Y, resignedMember.getResignYn());
    }
}