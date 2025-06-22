package com.example.oauth.member.controller;

import com.example.oauth.member.domain.Member;
import com.example.oauth.member.dto.MemberCreateDto;
import com.example.oauth.member.dto.MemberLoginDto;
import com.example.oauth.member.service.MemberService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.example.oauth.member.controller
 * fileName       : MemberController
 * author         : choisujin
 * date           : 2025. 6. 6.
 * description    : Member Controller
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 6. 6.        choisujin       최초 생성
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> memberCreate(@RequestBody MemberCreateDto memberCreateDto) {
        Member member = memberService.create(memberCreateDto);
        return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/doLogin")
    public ResponseEntity<?> doLogin(@RequestBody MemberLoginDto memberLoginDto) {
//        email, password 일치한지 검증
        Member member = memberService.login(memberLoginDto);

//        일치할 경우 jwt accesstoken 생성

        return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }
}
