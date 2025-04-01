package com.hotelmanagementsystem.indentity_service.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String tenDangNhap;
    private String role;

    public LoginResponse(String token, String tenDangNhap, String role) {
        this.token = token;
        this.tenDangNhap = tenDangNhap;
        this.role = role;
    }

    // Getters & Setters
}
