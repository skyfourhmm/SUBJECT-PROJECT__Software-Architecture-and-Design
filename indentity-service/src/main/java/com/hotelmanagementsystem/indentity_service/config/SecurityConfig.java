package com.hotelmanagementsystem.indentity_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()  // Cho phép các endpoint đăng ký và đăng nhập được truy cập công khai
                        .anyRequest().authenticated()  // Các request còn lại yêu cầu xác thực
                )
                .formLogin(withDefaults())  // Cấu hình form login mặc định, không cần gọi 'and()'
                .httpBasic(withDefaults())  // Cấu hình httpBasic mặc định, không cần gọi 'and()'
                .csrf(csrf -> csrf.disable());  // Tắt CSRF nếu không cần bảo vệ

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Cấu hình PasswordEncoder
    }
}


