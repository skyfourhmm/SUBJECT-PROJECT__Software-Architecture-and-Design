package com.hotelmanagementsystem.indentity_service.controller;

import com.hotelmanagementsystem.indentity_service.dto.LoginRequest;
import com.hotelmanagementsystem.indentity_service.dto.LoginResponse;
import com.hotelmanagementsystem.indentity_service.dto.RegisterRequest;
import com.hotelmanagementsystem.indentity_service.dto.ResponseDTO;
import com.hotelmanagementsystem.indentity_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequest registerRequest) {
        ResponseDTO response = authService.registerUser(registerRequest);
        if ("ERROR".equals(response.getStatus())) {
            return ResponseEntity.badRequest().body(response);  // Trả về mã 400 với thông báo lỗi
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO>  login(@RequestBody LoginRequest loginRequest) {
        ResponseDTO response = authService.loginUser(loginRequest);
        if ("ERROR".equals(response.getStatus())) {
            return ResponseEntity.badRequest().body(response);  // Trả về mã 400 với thông báo lỗi
        }
        return ResponseEntity.ok(response);
    }
}
