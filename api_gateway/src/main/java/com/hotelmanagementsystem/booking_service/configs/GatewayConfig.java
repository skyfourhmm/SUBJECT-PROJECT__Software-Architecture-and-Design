package com.hotelmanagementsystem.booking_service.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hotelmanagementsystem.booking_service.security.AuthenticationFilter;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter authFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("hotel-service", r -> r.path("/hotel/api/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://localhost:8083")) // service thật
                .build();
    }
}
