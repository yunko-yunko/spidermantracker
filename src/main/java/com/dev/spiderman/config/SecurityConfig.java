package com.dev.spiderman.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Set;

@Configuration
public class SecurityConfig {

    private static final Set<String> ALLOWED_IPS = Set.of(
            "211.204.33.72"
    );

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().access((authentication, context) -> {
                            HttpServletRequest request = context.getRequest();

                            String forwardedFor = request.getHeader("X-Forwarded-For");
                            String clientIp = forwardedFor != null && !forwardedFor.isBlank()
                                    ? forwardedFor.split(",")[0].trim()
                                    : request.getRemoteAddr();

                            return new AuthorizationDecision(ALLOWED_IPS.contains(clientIp));
                        })
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}