package com.hotelmanagementsystem.booking_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // Bỏ qua các đường dẫn public (không cần token)
        if (
        path.startsWith("/identity/api/auth/login") ||
        path.startsWith("/public-api") ||
        path.startsWith("/identity/api/auth/register") ||
        path.startsWith("/identity/api/customer/list") ||
        path.startsWith("/hotel/api/loai-phong/tat-ca") ||
        path.startsWith("/hotel/api/phong/loai/") ||
        path.startsWith("/identity/api/customer/by-sdt") ||
        path.startsWith("/booking/api/phieu-dat-phong") ||
        path.startsWith("/identity/api/customer/by-id") ||
        path.startsWith("/hotel/api/phong/by-id") 
        ) {
            return chain.filter(exchange);
        }


        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        try {
            Claims claims = JwtUtil.validateToken(token);

            // Optional: Thêm claims vào header nếu muốn forward xuống service
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("X-User-Id", claims.getSubject()) // Ví dụ: userId
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());

        } catch (JwtException e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return -1; // Ưu tiên chạy sớm
    }
}
