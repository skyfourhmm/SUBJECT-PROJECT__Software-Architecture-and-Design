package com.hotelmanagementsystem.indentity_service.repository;

import com.hotelmanagementsystem.indentity_service.entity.KhachHang;
import com.hotelmanagementsystem.indentity_service.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, String> {
    Optional<KhachHang> findByTaiKhoan_TenDangNhap(String tenDangNhap); // Nếu cần tìm theo tài khoản

    Optional<KhachHang> findByTaiKhoan(TaiKhoan taiKhoan);

    // Optional: nếu cần tìm thêm
    Optional<KhachHang> findBySoDienThoai(String soDienThoai);

    // KhachHangRepository.java
    boolean existsBySoDienThoai(String soDienThoai);
    boolean existsByCCCD(String CCCD);

}
