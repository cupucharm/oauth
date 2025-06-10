package com.example.oauth.member.repository;

import com.example.oauth.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * packageName    : com.example.oauth.member.repository
 * fileName       : MemberRepository
 * author         : choisujin
 * date           : 2025. 6. 5.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 6. 5.        choisujin       최초 생성
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
