package com.example.authenticationserver.exceptions;

import org.springframework.security.core.AuthenticationException;

public class PasswordEmailAuthenticationException extends AuthenticationException {
    public PasswordEmailAuthenticationException(String msg) {
        super(msg);
    }
}
