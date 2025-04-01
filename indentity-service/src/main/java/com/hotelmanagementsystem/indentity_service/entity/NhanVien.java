package com.hotelmanagementsystem.indentity_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maNhanVien")
    private Long maNhanVien;

    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private String CCCD;
    private String anhThe;

    @ManyToOne
    @JoinColumn(name = "maLoaiNhanVien", nullable = false)
    private LoaiNhanVien loaiNhanVien;

    @OneToOne
    @JoinColumn(name = "tenDangNhap")
    private TaiKhoan taiKhoan;

    // Getters & Setters
}
