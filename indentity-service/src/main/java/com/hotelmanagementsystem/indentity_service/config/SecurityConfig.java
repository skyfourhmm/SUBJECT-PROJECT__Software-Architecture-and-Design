package com.hotelmanagementsystem.indentity_service.config;

import com.hotelmanagementsystem.indentity_service.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/register", "/api/auth/login", "/api/auth/logout").permitAll()  // Cho phép các endpoint đăng ký và đăng nhập được truy cập công khai
                        .requestMatchers("/api/customer/me").authenticated()
                        .requestMatchers("/api/employee/me").authenticated()
                        .requestMatchers("/api/customer/**").hasAnyRole("EMPLOYEE", "OWNER")
                        .anyRequest().authenticated()  // Các request còn lại yêu cầu xác thực
                )
                .csrf(csrf -> csrf.disable());  // Tắt CSRF nếu không cần bảo vệ

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Cấu hình PasswordEncoder
    }
}


