package com.hotelmanagementsystem.booking_service.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PhieuDatPhongRequest {
    private String maKhachHang;
    private String maPhong;
    private String maHoaDon;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double tienCoc;
    private String trangThai;
    private String moTa;
}