package com.express.application.port.output.security;

import com.express.adapter.common.security.TokenInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface SecurityProcessor {
    TokenInfo generateToken(String email, Collection<? extends GrantedAuthority> authorities);
    String createAccessToken(String email, String authorities);
    String createRefreshToken(String email);

    boolean validateToken(String email);
    String getEmailFromAccessToken(String accessToken);

    Authentication getAuthentication(String token);

    String resolveToken(HttpServletRequest request);

    TokenInfo setAuthentication(String email, String password);
}
