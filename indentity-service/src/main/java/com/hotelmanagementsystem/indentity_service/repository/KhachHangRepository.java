package com.hotelmanagementsystem.indentity_service.repository;

import com.hotelmanagementsystem.indentity_service.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, String> {
    // Các phương thức truy vấn cho KhachHang (nếu cần)
}
