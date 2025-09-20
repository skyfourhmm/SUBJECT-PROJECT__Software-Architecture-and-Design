package com.hotelmanagementsystem.indentity_service.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String tenDangNhap;
    private String vaiTro; // "KHACH_HANG" hoặc "NHAN_VIEN"
    private Object thongTinNguoiDung;

    public LoginResponse(String token, String tenDangNhap, String vaiTro, Object thongTinNguoiDung) {
        this.token = token;
        this.tenDangNhap = tenDangNhap;
        this.vaiTro = vaiTro;
        this.thongTinNguoiDung = thongTinNguoiDung;
    }

    // Getters & Setters nếu không dùng Lombok
}

