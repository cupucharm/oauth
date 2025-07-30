package com.example.oauth.common.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // servletRequest 사용자의 모든 요청 값이 담겨있음
        // servletResponse을 통해서 사용자에게 응답을 줄 수 있음
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String token = httpServletRequest.getHeader("Authorization");

        try {
            if (token != null) {
                if(!token.substring(0, 7).equals("Bearer ")) {
                    throw new AuthenticationServiceException("Bearer 형식이 아닙니다.");
                }

                String jwtToken = token.substring(7);
                // token 검증 및 claims(payload) 추출
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(jwtToken)   // 검증 후
                        .getBody();                 // Claims 꺼냄

                // Authentication 객체 생성
                UserDetails userDetails = new User(claims.getSubject(), "", 권한);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, jwtToken, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // 로그인 하는 순간 authentication 객체가 만들어지고, 그걸 프로젝트 전체에서 쉽게 꺼내다가 가져다 쓸 수 있음.
                // 사용 예시
                SecurityContextHolder.getContext().getAuthentication().getName(); // claims.getSubject() 꺼냄 // email
                SecurityContextHolder.getContext().getAuthentication().getCredentials(); // jwtToken 꺼냄
                SecurityContextHolder.getContext().getAuthentication().getAuthorities(); // userDetails.getAuthorities() 권한 꺼냄
            }

            // servletRequest 객체 안에서 토큰 꺼내기


            // 다시 체인으로 돌아가라
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write("invalid token");
        }

    }
}
