package com.hotelmanagementsystem.indentity_service.repository;

import com.hotelmanagementsystem.indentity_service.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {}
