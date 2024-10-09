package com.express.adapter.common.security;

import com.express.application.port.output.security.SecurityProcessor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
    GenericFilterBean vs OncePerRequestFilter
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final SecurityProcessor securityProcessor;
    private final String UTF_8 = "utf-8";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // 1. Request Header 로부터 Access Token을 추출한다.
            String token = securityProcessor.resolveToken(request);

            // 2. 추출한 Token의 유효성 검사를 진행한다.
            if (token != null && securityProcessor.validateToken(token)) {
                // Token이 유효할 경우, Authentication 객체를 생성하여 SecurityContext에 저장한다.
                Authentication authentication = securityProcessor.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
            logger.info("Exception in JwtAuthenticationFilter: " + e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setCharacterEncoding(UTF_8);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);


        }
    }
}
