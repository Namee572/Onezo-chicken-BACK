package com.green.onezo.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

//    Optional<Member> findByEmail(String email);

//    Member findMemberByUser_id(Long id);

    Optional<Member> findByUserIdAndPassword(String userId, String password);

    Optional<Member> findByUserId(String memberId);


    boolean existsByUserId(String userId);

    boolean existsByNickname(String nickname);
}