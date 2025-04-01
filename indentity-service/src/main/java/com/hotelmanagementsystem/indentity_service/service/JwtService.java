package com.hotelmanagementsystem.indentity_service.service;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY = "7U9jJhD0U/VgP6G8bTh3yKsqE9QoOwXcUzJzJq3wFk8="; // Thay bằng key mạnh hơn

    public String generateToken(String username, String role) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 ngày
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
