package com.hotelmanagementsystem.indentity_service.security;

import com.hotelmanagementsystem.indentity_service.entity.TaiKhoan;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;
    private final Set<String> tokenBlacklist = new HashSet<>();

    public void addToBlacklist(String token) {
        tokenBlacklist.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklist.contains(token);
    }


    public String generateToken(TaiKhoan taiKhoan) {
        // Tạo JWT token với thông tin của người dùng
        return Jwts.builder()
                .setSubject(taiKhoan.getTenDangNhap())
                .claim("role", taiKhoan.getRole().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token hết hạn sau 1 ngày
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }


    public boolean validateToken(String token) {
        try {
            if (isTokenBlacklisted(token)) {
                return false;
            }

            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();  // "sub" là tên đăng nhập (username)
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

}
