package com.hotelmanagementsystem.booking_service.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.http.server.reactive.ServerHttpResponse;

import org.springframework.http.HttpHeaders;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GatewayFilter {

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

        // Bỏ qua xác thực cho các đường dẫn như /auth/**
        if (request.getURI().getPath().contains("/auth")) {
            return chain.filter(exchange);
        }

        // Kiểm tra có Authorization header không
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            return this.onError(exchange, "Thiếu Authorization header", HttpStatus.UNAUTHORIZED);
        }

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return this.onError(exchange, "Token không hợp lệ", HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7);

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            // Lấy thông tin user từ token
            String userId = claims.getSubject();

            // Chuyển thêm userId qua header cho service phía sau nếu cần
            ServerWebExchange mutatedExchange = exchange.mutate()
                    .request(builder -> builder.header("X-User-Id", userId))
                    .build();

            return chain.filter(mutatedExchange);

        } catch (Exception e) {
            return this.onError(exchange, "Token sai hoặc hết hạn", HttpStatus.UNAUTHORIZED);
        }
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }
}
