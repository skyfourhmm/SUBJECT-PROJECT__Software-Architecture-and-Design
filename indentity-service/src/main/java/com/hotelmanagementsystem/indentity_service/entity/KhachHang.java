package com.hotelmanagementsystem.indentity_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KhachHang")
public class KhachHang {
    @Id
    @Column(name = "maKhachHang", updatable = false, nullable = false)
    private String maKhachHang = UUID.randomUUID().toString();

    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private int diemThuong;
    private String ghiChu;

    @OneToOne
    @JoinColumn(name = "taiKhoan_id", referencedColumnName = "id")
    private TaiKhoan taiKhoan;


    // Getters & Setters
}

