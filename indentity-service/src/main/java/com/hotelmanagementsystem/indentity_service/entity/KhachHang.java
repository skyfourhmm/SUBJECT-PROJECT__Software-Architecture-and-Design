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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maKhachHang")
    private Long maKhachHang;

    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private int diemThuong;
    private String ghiChu;

    @OneToOne
    @JoinColumn(name = "tenDangNhap")
    private TaiKhoan taiKhoan;


    @PrePersist
    private void generateMaKhachHang() {
        // Tạo mã ngẫu nhiên cho khách hàng
        this.maKhachHang = generateRandomCode();
    }

    private Long generateRandomCode() {
        // Sử dụng Random để tạo ra một số ngẫu nhiên và kết hợp với tiền tố "KH"
        long randomNum = ThreadLocalRandom.current().nextLong(1000000000000L, 9999999999999L); // Số ngẫu nhiên từ 12 chữ số
        return randomNum;
    }

    // Getters & Setters
}

