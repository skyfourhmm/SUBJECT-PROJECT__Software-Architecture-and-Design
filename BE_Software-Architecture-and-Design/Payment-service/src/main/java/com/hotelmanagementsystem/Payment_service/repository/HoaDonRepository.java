package com.hotelmanagementsystem.Payment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotelmanagementsystem.Payment_service.entity.HoaDon;

public interface HoaDonRepository extends JpaRepository<HoaDon, String> {
    // JpaRepository đã đủ cho CRUD cơ bản
}
