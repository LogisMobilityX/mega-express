package com.express.adapter.output.security;

import com.express.adapter.common.WebAdapter;
import com.express.adapter.common.security.TokenInfo;
import com.express.application.port.output.security.SecurityProcessor;
import com.express.application.port.output.user.UserReader;
import com.express.domain.model.user.SecurityCustomUser;
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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
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

    private static final String BEARER = "Bearer";

    private final AuthenticationManagerBuilder authenticationManagerBuilder;



    @Override
    public TokenInfo generateToken(Authentication authentication) {
        String accessToken = createAccessToken(authentication);
        String refreshToken = createRefreshToken(authentication);
        return TokenInfo.builder()
                .certificateType(BEARER)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public String createAccessToken(Authentication authentication) {
        Claims claims = Jwts.claims().setSubject(authentication.getName());
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + accessExpirationTime);

        log.info("createAccessToken : " + authentication.getName() + " || " + LocalDateTime.now());
        String authoritiesToString = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        SecurityCustomUser userDetails = (SecurityCustomUser) authentication.getPrincipal();

        return Jwts.builder()
                .setClaims(claims)
                .claim("auth" , authoritiesToString)
                .claim("userId", userDetails.getUser().getUserId().Id())
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /*
    refresh는 access 재발급 용도이기 때문에 따로 JWT Payload에 정보를 추가 할 필요가 없다.
     */
    @Override
    public String createRefreshToken(Authentication authentication) {
        log.info("createRefreshToken : " + authentication.getName() + " || " + LocalDateTime.now());
        Claims claims = Jwts.claims().setSubject(authentication.getName());
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + refreshExpirationTime);
        String authoritiesToString = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        SecurityCustomUser userDetails = (SecurityCustomUser) authentication.getPrincipal();

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .claim("auth" , authoritiesToString)
                .claim("userId", userDetails.getUser().getUserId().Id())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();


        return refreshToken;
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch(ExpiredJwtException e) {
            log.error("Token Expires");
            throw new RuntimeException("Token Expires");
        } catch(JwtException e) {
            log.error("Invalid Access Token Type");
            throw new RuntimeException("Invalid Access Token Type");
        } catch (IllegalArgumentException e) {
            log.error("Header empty");
            throw new RuntimeException("Header empty");
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


        return new UsernamePasswordAuthenticationToken(principal, claims.get("userId").toString(), authorities);
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

        return generateToken(authentication);
    }

    @Override
    public Long currentUserId() {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return userId;
    }


}
