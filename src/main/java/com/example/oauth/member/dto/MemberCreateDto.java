package com.example.oauth.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.example.oauth.member.dto
 * fileName       : MemberCreateDto
 * author         : choisujin
 * date           : 2025. 6. 9.
 * description    : 회원가입 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 6. 9.        choisujin       최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateDto {
    private String email;
    private String password;
}
