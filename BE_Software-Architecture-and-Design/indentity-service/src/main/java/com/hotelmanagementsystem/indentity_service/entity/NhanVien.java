package com.hotelmanagementsystem.indentity_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private LocalDate ngaySinh;
    private String soDienThoai;
    private String cccd;
    private String diaChi;
    private String trangThai;
    private String anhThe;

    // Quan hệ 1-1 với TaiKhoan (NhanVien là chủ sở hữu)
    @OneToOne
    @JoinColumn(name = "maTaiKhoan")
    @JsonBackReference
    private TaiKhoan taiKhoan;


    // Quan hệ nhiều-1 với LoaiNhanVien
    @ManyToOne
    @JoinColumn(name = "maLoaiNhanVien") // FK trỏ tới bảng LoaiNhanVien
    private LoaiNhanVien loaiNhanVien;
}
