package com.express.application.port.output.security;

import com.express.adapter.common.security.TokenInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface SecurityProcessor {
    TokenInfo generateToken(Authentication authentication);
    String createAccessToken(Authentication authentication);
    String createRefreshToken(Authentication authentication);

    boolean validateToken(String token);
    String getEmailFromAccessToken(String accessToken);

    Authentication getAuthentication(String token);

    String resolveToken(HttpServletRequest request);

    TokenInfo setAuthentication(String email, String password);
}
