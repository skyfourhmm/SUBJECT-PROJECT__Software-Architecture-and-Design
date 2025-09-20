package com.hotelmanagementsystem.booking_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class JwtUtil {
    private static final String SECRET_KEY = "cXWgolhZuRwCyI0mQGb1UtrtIrMLbpfL7TxvjghQnPi9M7NFU6o8Mp0Vk4s67s4T12wbuO84LbEsfuymJyzMeArr"; // Ít nhất 256 bit

    public static Claims validateToken(String token) {
    Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    try {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey("cXWgolhZuRwCyI0mQGb1UtrtIrMLbpfL7TxvjghQnPi9M7NFU6o8Mp0Vk4s67s4T12wbuO84LbEsfuymJyzMeArr")
                .build()
                .parseClaimsJws(token)
                .getBody();


        return claims;

    } catch (JwtException e) {
        System.out.println("JWT validation failed: " + e.getMessage());
        throw e;  // hoặc return null, tùy logic bạn muốn xử lý
    }
}


}