package com.edu.kt.gw.simple.security;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    JwtAuthenticationException(String msg) {
        super(msg);
    }
}
