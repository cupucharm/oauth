package com.example.oauth.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * packageName    : com.example.oauth.common.config
 * fileName       : SecurityConfig
 * author         : choisujin
 * date           : 2025. 6. 9.
 * description    : SecurityConfig 암호화
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 6. 9.        choisujin       최초 생성
 */
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder makePassword() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain myFilter(HttpSecurity httpSecurity) throws Exception {
        // 시큐리티 설정
        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 같은 도메인끼리만 api를 통해서 데이터를 주고 받겠다는 설정.
                .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화 -- 보안 공격 중 하나 mvc 패턴
                // Basic 인증 비활성화
                // Basic 인증은 사용자이름과 비밀번호를 Base64로 인코딩하여 인증값으로 활용 -- 암호화가 아님, 인코딩임
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("*"));    // 모든 HTTP메서드 허용
        configuration.setAllowedHeaders(Arrays.asList("*"));    // 모든 헤더값 허용
        configuration.setAllowCredentials(true);                // 자격증명 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 모든 url 패턴에 대해서 cors 허용 설정
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
