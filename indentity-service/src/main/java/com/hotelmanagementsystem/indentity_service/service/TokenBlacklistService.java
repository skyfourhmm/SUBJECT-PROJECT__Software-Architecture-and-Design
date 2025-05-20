package com.hotelmanagementsystem.indentity_service.service;

import com.hotelmanagementsystem.indentity_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {

    private final RedisTemplate<String, String> redisTemplate;
    private final JwtUtil jwtUtil;

    public void blacklistToken(String token) {
        // Lấy thời gian hết hạn của token
        Date expirationDate = jwtUtil.getExpirationDateFromToken(token);
        long ttl = (expirationDate.getTime() - System.currentTimeMillis()) / 1000; // giây

        // Lưu token vào Redis với TTL bằng thời gian còn lại của token
        redisTemplate.opsForValue().set(token, "blacklisted", ttl, TimeUnit.SECONDS);
    }

    public boolean isTokenBlacklisted(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(token));
    }
}
