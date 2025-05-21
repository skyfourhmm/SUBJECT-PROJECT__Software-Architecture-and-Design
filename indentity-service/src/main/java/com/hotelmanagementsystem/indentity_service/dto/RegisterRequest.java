package com.hotelmanagementsystem.indentity_service.dto;

import lombok.Data;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class RegisterRequest {
    private String tenDangNhap;
    private String matKhau;

    private String loaiNguoiDung; // "khachHang" hoặc "nhanVien"

    // Thông tin Khách/Nhân viên
    private String hoTen;
    private String gioiTinh;
    private LocalDate ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private String ghiChu;
    private String cccd;
}
