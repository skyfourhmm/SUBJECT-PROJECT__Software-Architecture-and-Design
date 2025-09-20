package com.hotelmanagementsystem.Payment_service.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "hoa_don")
public class HoaDon {

    @Id

    @Column(name = "maHoaDon", updatable = false, nullable = false)
    private String maHoaDon = UUID.randomUUID().toString();

    @Column(name = "thoi_gian_tao", nullable = false)
    private LocalDateTime thoiGianTao;

    @Column(name = "trang_thai", nullable = false)
    private String trangThai;

    @Column(name = "thoi_gian_thanh_toan")
    private LocalDateTime thoiGianThanhToan;

    @Column(name = "ma_phieu_dat", nullable = false)
    private UUID maPhieuDat;

    @Column(name = "gia_tien", nullable = false)
    private Double giaTien;

    @Column(name = "ma_khach_hang", nullable = false)
    private UUID maKhachHang;

    @Column(name = "ma_nhan_vien", nullable = false)
    private UUID maNhanVien;

}