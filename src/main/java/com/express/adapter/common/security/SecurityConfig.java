package com.express.adapter.common.security;

import com.express.application.port.output.security.SecurityProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final SecurityProcessor securityProcessor;
    /*
    spring security 버전업으로 인한 코드 형태 변경됨
     */
    private final ObjectMapper objectMapper;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/v1/api/user/auth/login").permitAll()
                                .requestMatchers("/v1/api/user/auth/reissue").permitAll()
                                .requestMatchers("/v1/api/user/join/").permitAll()
                                .requestMatchers("/v1/api/user/auth/certifiedEmail/send").permitAll()
                                .requestMatchers("/v1/api/user/auth/certifiedEmail").permitAll()
                                .requestMatchers("/error").permitAll()
                                .anyRequest()
                                .authenticated()
        ).httpBasic(Customizer.withDefaults())
                .csrf((csrf) -> csrf.disable()) // REST API이므로 basic auth 및 csrf 보안을 사용하지 않음
                .sessionManagement(
                        (sessionManagement) ->
                                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT를 사용하기 때문에 세션을 사용하지 않음
                )
                .addFilterBefore(
                        new JwtAuthenticationFilter(securityProcessor),
                        UsernamePasswordAuthenticationFilter.class
                ).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt Encoder 사용
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
