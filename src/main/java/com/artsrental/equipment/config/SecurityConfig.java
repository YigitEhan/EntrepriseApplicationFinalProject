package com.artsrental.equipment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security Configuration (Phase B).
 *
 * Phase B: Session-based authentication with form login.
 * - BCrypt password hashing
 * - Form-based login
 * - Authorization rules for different endpoints
 * - CSRF and frame options configured for H2 console in dev mode
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
     * Authorization rules:
     * - Public: /register, /login, /css/**, /h2-console/** (dev only)
     * - Authenticated: /catalog, /cart/**, /checkout/**
     *
     * @param http HttpSecurity configuration
     * @return SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/", "/register", "/login", "/css/**", "/h2-console/**").permitAll()
                // Authenticated endpoints
                .requestMatchers("/catalog", "/cart/**", "/checkout/**").authenticated()
                // All other requests require authentication
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/catalog", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            // CSRF and frame options for H2 console (dev mode only)
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            );

        return http.build();
    }
}

