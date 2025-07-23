package com.example.oauth.common.auth;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * packageName    : com.example.oauth.common.auth
 * fileName       : JwtTokenFilter
 * author         : choisujin
 * date           : 2025. 6. 9.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 6. 9.        choisujin       최초 생성
 */
@Component
public class JwtTokenFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // servletRequest 사용자의 모든 요청 값이 담겨있음
        // servletResponse을 통해서 사용자에게 응답을 줄 수 있음

        // servletRequest 객체 안에서 토큰 꺼내기


        // 다시 체인으로 돌아가라
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
