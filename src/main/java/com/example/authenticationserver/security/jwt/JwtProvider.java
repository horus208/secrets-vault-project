package com.example.authenticationserver.security.jwt;

import org.springframework.security.core.Authentication;

public interface JwtProvider {
    public String generateToken(String email);
    public Authentication validate(String token);
}
