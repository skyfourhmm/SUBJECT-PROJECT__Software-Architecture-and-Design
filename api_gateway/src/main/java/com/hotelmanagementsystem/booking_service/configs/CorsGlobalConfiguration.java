package com.hotelmanagementsystem.booking_service.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsGlobalConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);  // Cho phép gửi cookie/token
        config.addAllowedOrigin("http://localhost:3000");  // Cho phép FE
        config.addAllowedHeader("*");      // Cho phép tất cả headers
        config.addAllowedMethod("*");      // Cho phép tất cả method: GET, POST,...

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}