package com.hotelmanagementsystem.hotel_service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementsystem.hotel_service.entity.Phong;

public interface PhongRepository extends JpaRepository<Phong, String> {
    // không cần thêm method nào, JpaRepository đã hỗ trợ findAll()
    List<Phong> findByTrangThaiPhong_TenTrangThai(String tinhTrangPhong);

    List<Phong> findByLoaiPhong_MaLoaiPhong(String maLoaiPhong);

    
}