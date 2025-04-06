package com.hotelmanagementsystem.indentity_service.repository;

import com.hotelmanagementsystem.indentity_service.entity.LoaiNhanVien;
import com.hotelmanagementsystem.indentity_service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoaiNhanVienRepository extends JpaRepository<LoaiNhanVien, String> {
    LoaiNhanVien findByTenLoaiNhanVien(String tenLoaiNhanVien);
    Optional<LoaiNhanVien> findByRole(Role role);
}
