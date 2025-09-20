package com.hotelmanagementsystem.indentity_service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TaiKhoan")
public class TaiKhoan {
    @Id
    @Column(name = "maTaiKhoan", updatable = false, nullable = false)
    private String maTaiKhoan = UUID.randomUUID().toString();

    private String tenDangNhap;
    private String matKhau;
    private String trangThai;

    @OneToOne(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    @JsonManagedReference
    private NhanVien nhanVien;

    @OneToOne(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    @JsonManagedReference
    private KhachHang khachHang;

}