package com.express.adapter.output.security;

import com.express.adapter.common.WebAdapter;
import com.express.adapter.common.security.TokenInfo;
import com.express.application.port.output.security.SecurityProcessor;
import com.express.application.port.output.user.UserReader;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Getter
@WebAdapter
@RequiredArgsConstructor
public class SecurityAdapter implements SecurityProcessor{
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.token.access-expiration-time}")
    private long accessExpirationTime;

    @Value("${jwt.token.refresh-expiration-time}")
    private long refreshExpirationTime;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserReader userReader;
    private static final String BEARER = "Bearer";

    @Override
    public TokenInfo generateToken(String email, Collection<? extends GrantedAuthority> authorities) {
        String authoritiesToString = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        String accessToken = createAccessToken(email, authoritiesToString);
        String refreshToken = createRefreshToken(email);
        return TokenInfo.builder()
                .certificateType(BEARER)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public String createAccessToken(String email, String authorities) {
        log.info("createAccessToken : " + email + " || " + LocalDateTime.now());
        return Jwts.builder()
                //key id
                .setSubject(email)
                .claim("auth", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessExpirationTime))
                //signature
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /*
    refresh는 access 재발급 용도이기 때문에 따로 JWT Payload에 정보를 추가 할 필요가 없다.
     */
    @Override
    public String createRefreshToken(String email) {
        log.info("createRefreshToken : " + email + " || " + LocalDateTime.now());
        String refreshToken = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return refreshToken;
    }

    @Override
    public boolean validateToken(String email) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(email);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String getEmailFromAccessToken(String accessToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(accessToken)
                .getBody();
        return claims.getSubject();
    }

    @Override
    public Authentication getAuthentication(String token) {
        // Jwt 토큰 복호화
        Claims claims = parseClaims(token);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication return
        // UserDetails: interface, User: UserDetails를 구현한 class
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    @Override
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        log.debug("bearertoken : {}", bearerToken);
        if (StringUtils.hasText(bearerToken)) {
            if (bearerToken.startsWith("Bearer") && bearerToken.length() > 7) {
                int tokenStartIndex = 7;
                return bearerToken.substring(tokenStartIndex);
            }
            throw new RuntimeException("임시처리");
        }

        return bearerToken;
    }

    @Override
    public TokenInfo setAuthentication(String email, String password) {
        // 1. id, password 기반 Authentication 객체 생성, 해당 객체는 인증 여부를 확인하는 authenticated 값이 false.
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        // 2. 검증 진행 - CustomUserDetailsService.loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);

        return generateToken(authentication.getName(),authentication.getAuthorities());
    }


}
