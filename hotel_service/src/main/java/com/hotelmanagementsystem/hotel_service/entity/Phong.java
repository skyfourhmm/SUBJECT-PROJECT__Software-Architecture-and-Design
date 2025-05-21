package com.hotelmanagementsystem.hotel_service.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "phong")
public class Phong {

    @Id
    @Column(name = "ma_phong",updatable = false, nullable = false)
    private String maPhong = UUID.randomUUID().toString();;

    @ManyToOne
    @JoinColumn(name = "ma_loai_phong", referencedColumnName = "ma_loai_phong")
    private LoaiPhong loaiPhong;

    @ManyToOne
    @JoinColumn(name = "ma_trang_thai", referencedColumnName = "ma_trang_thai")
    private TrangThaiPhong trangThaiPhong;

    @Column(name = "ten_phong")
    private String tenPhong;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "tinh_trang_phong")
    private String tinhTrangPhong;

    // constructor, getter, setter
}