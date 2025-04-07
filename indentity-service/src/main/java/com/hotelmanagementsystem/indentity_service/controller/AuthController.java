package com.hotelmanagementsystem.indentity_service.controller;

import com.hotelmanagementsystem.indentity_service.dto.*;
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

    @PostMapping("/logout")
    public ResponseEntity<ResponseDTO> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(new ResponseDTO("Token không hợp lệ.", "ERROR"));
        }

        String token = authHeader.substring(7); // Bỏ "Bearer "
        ResponseDTO response = authService.logoutUser(token);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/change-password")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
                                                      @RequestHeader("Authorization") String authHeader) {

        System.out.println("authHeader" + authHeader);
        String token = authHeader.substring(7);  // Bỏ "Bearer " ra
        ResponseDTO response = authService.changePassword(changePasswordRequest, token);
        return ResponseEntity.ok(response);
    }

}
