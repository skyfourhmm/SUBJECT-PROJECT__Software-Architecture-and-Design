package com.hotelmanagementsystem.indentity_service.dto;

import com.hotelmanagementsystem.indentity_service.entity.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class RegisterRequest {
    private String tenDangNhap;
    private String matKhau;
    private Role role; // OWNER, EMPLOYEE, CUSTOMER

    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String soDienThoai;
}
