package com.hotelmanagementsystem.hotel_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementsystem.hotel_service.entity.LoaiPhong;

public interface LoaiPhongRepository extends JpaRepository<LoaiPhong, String> {
}
