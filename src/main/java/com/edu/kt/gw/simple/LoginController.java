package com.edu.kt.gw.simple;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final PasswordEncoder passwordEncoder;
    private final ReactiveUserDetailsService userDetailsService;
    private final TokenProvider tokenProvider;
    private final DecryptService decryptService;

    @PostMapping("/api/login")
    Mono<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaaaaaa" + loginRequest);
        return decryptService.login(loginRequest.password())
                .flatMap(decryptedPassword -> userDetailsService.findByUsername(loginRequest.username())
                        .filter(u -> passwordEncoder.matches(decryptedPassword, u.getPassword()))
                        .map(tokenProvider::generateToken)
                        .map(LoginResponse::new)
                        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"))));
    }
}
