package com.artsrental.equipment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security Configuration (Phase A - Minimal).
 * 
 * Phase A: Permits all requests to allow testing of catalog functionality.
 * Phase B: Will add proper authentication, authorization, and session management.
 * 
 * Security Features (to be implemented in Phase B):
 * - BCrypt password encoding
 * - Session-based authentication
 * - Role-based access control
 * - CSRF protection
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    /**
     * BCrypt password encoder bean.
     * Used for hashing passwords (never store plaintext).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Security filter chain configuration.
     * 
     * Phase A: Permits all requests for development.
     * Phase B: Will add login, logout, and protected routes.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()  // Phase A: Allow all requests
            )
            .csrf(csrf -> csrf.disable())  // Disabled for Phase A (will enable in Phase B)
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())  // Allow H2 console
            );
        
        return http.build();
    }
}

