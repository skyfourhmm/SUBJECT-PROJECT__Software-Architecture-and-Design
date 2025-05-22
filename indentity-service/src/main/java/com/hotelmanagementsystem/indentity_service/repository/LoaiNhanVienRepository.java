package com.hotelmanagementsystem.indentity_service.repository;

import com.hotelmanagementsystem.indentity_service.entity.LoaiNhanVien;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoaiNhanVienRepository extends JpaRepository<LoaiNhanVien, String> {
    LoaiNhanVien findByTenLoaiNhanVien(String tenLoaiNhanVien);
}
