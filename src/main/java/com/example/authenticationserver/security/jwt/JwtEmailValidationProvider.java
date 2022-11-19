package com.example.authenticationserver.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import java.util.Map;
@Service
public class JwtEmailValidationProvider implements JwtProvider{
    private final String secret = "justSimpleSecretKeyToPractise:123456789123456789123456789";
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));


    @Override
    public String generateToken(String email) {



        String jwt = Jwts.builder()
                .setClaims(Map.of("email", email))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*600))
                .signWith(key).compact();

        return jwt;

    }

    @Override
    public Authentication validate(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        String email = String.valueOf(claims.get("email"));
        Authentication auth = new UsernamePasswordAuthenticationToken(email,null,null);

        return auth;
    }
}
