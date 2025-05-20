package com.hotelmanagementsystem.indentity_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

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
    private LocalDate ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private int diemThuong;
    private String ghiChu;

    // Quan hệ 1-1 với TaiKhoan (KhachHang là chủ sở hữu)
    @OneToOne
    @JoinColumn(name = "maTaiKhoan")
    @JsonBackReference
    private TaiKhoan taiKhoan;

}


