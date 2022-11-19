package com.example.authenticationserver.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    USER("USER"),ADMIN("ADMIN");

    String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
