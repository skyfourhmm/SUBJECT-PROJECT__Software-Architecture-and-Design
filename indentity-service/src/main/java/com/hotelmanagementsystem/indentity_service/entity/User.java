package com.hotelmanagementsystem.indentity_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // Tên đăng nhập

    @Column(nullable = false)
    private String passwordHash; // Mật khẩu đã hash (bcrypt)

    @Column(nullable = false)
    private Boolean isActive = true; // Tài khoản có đang hoạt động không

    @Column(nullable = false)
    private String fullName; // Họ tên đầy đủ

    @Column(nullable = false, unique = true)
    private String email; // Email đăng ký

    private String phoneNumber; // Số điện thoại

    private String avatarUrl; // Ảnh đại diện

}
