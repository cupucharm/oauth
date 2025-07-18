package com.example.oauth.common.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

/**
 * packageName    : com.example.oauth.common.auth
 * fileName       : JwtTokenProvider
 * author         : choisujin
 * date           : 2025. 6. 9.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 6. 9.        choisujin       최초 생성
 */
@Component
public class JwtTokenProvider {
    private final String secretKey;
    private final int expiration;
    private Key SECRET_KEY;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration}") int expiration) {
        this.secretKey = secretKey;
        this.expiration = expiration;
        this.SECRET_KEY = new SecretKeySpec(java.util.Base64.getDecoder().decode(secretKey), SignatureAlgorithm.HS512.getJcaName());
    }

    public String createToken(String email, String role) {
        // Claims는 JWT 토큰의 payload를 의미
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);

        Date now = new Date();
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiration*60*1000L))
                .signWith(SECRET_KEY)
                .compact();
        return token;
    }
}
