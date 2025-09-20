package com.hotelmanagementsystem.indentity_service.repository;

import com.hotelmanagementsystem.indentity_service.entity.NhanVien;
import com.hotelmanagementsystem.indentity_service.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
    Optional<NhanVien> findByTaiKhoan(TaiKhoan taiKhoan);
     // ✅ Kiểm tra trùng số điện thoại
    boolean existsBySoDienThoai(String soDienThoai);

    // ✅ Kiểm tra trùng CCCD
    boolean existsByCccd(String cccd);
}
