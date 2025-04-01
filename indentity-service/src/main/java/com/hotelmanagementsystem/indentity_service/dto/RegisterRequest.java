package com.hotelmanagementsystem.indentity_service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterRequest {
    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private String tenDangNhap;
    private String matKhau;
    private String role; // CUSTOMER, EMPLOYEE, OWNER
}
