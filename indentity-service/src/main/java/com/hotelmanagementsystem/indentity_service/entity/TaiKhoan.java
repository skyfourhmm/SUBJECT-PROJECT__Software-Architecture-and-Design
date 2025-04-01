package com.hotelmanagementsystem.indentity_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TaiKhoan")
public class TaiKhoan {
    @Id
    @Column(name = "tenDangNhap", unique = true, nullable = false)
    private String tenDangNhap;

    @Column(name = "matKhau", nullable = false)
    private String matKhau;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role; // OWNER, EMPLOYEE, CUSTOMER

    @Column(name = "trangThai", nullable = false)
    private boolean trangThai;
}
