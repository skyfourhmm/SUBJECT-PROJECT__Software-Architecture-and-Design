package com.hotelmanagementsystem.indentity_service.security;

import com.hotelmanagementsystem.indentity_service.entity.TaiKhoan;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;


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


}
