package com.example.oauth.member.controller;

import com.example.oauth.member.service.MemberService;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.example.oauth.member.controller
 * fileName       : MemberController
 * author         : choisujin
 * date           : 2025. 6. 6.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 6. 6.        choisujin       최초 생성
 */
@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
