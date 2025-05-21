package com.hotelmanagementsystem.booking_service.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // 🔥 Disable CSRF cho WebFlux
                .authorizeExchange(exchange -> exchange
                        .anyExchange().permitAll() // Cho phép tất cả các route
                )
                .build();
    }
}