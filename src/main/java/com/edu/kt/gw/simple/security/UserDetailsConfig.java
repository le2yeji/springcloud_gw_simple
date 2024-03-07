package com.edu.kt.gw.simple.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class UserDetailsConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    MapReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        var user = User.builder()
                .username("edu")
                .password(passwordEncoder().encode("edu1234"))
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }
}
