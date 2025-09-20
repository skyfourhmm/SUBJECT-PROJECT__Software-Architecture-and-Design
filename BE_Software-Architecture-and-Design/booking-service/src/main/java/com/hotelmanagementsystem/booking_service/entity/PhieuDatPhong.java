package com.hotelmanagementsystem.booking_service.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PhieuDatPhong")
public class PhieuDatPhong {
    @Id
    @Column(name = "ma_phieu_dat_phong", updatable = false, nullable = false)
    private String maPhieuDat = UUID.randomUUID().toString();

    private String maKhachHang;
    private String maPhong;
    private String maHoaDon;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double tienCoc;
    private String trangThai;
    private String moTa;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters, setters, constructor
}
