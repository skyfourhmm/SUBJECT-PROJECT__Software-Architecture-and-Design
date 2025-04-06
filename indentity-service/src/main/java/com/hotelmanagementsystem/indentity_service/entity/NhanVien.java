package com.hotelmanagementsystem.indentity_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @Column(name = "maNhanVien", updatable = false, nullable = false)
    private String maNhanVien = UUID.randomUUID().toString();

    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private String anhThe;

    @ManyToOne
    @JoinColumn(name = "maLoaiNhanVien", nullable = false)
    private LoaiNhanVien loaiNhanVien;

    @OneToOne
    @JoinColumn(name = "taiKhoan_id", referencedColumnName = "id")
    private TaiKhoan taiKhoan;
    // Getters & Setters
}
