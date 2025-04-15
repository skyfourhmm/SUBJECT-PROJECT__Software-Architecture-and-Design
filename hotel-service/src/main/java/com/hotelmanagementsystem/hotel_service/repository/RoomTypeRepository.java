package com.hotelmanagementsystem.hotel_service.repository;

import com.hotelmanagementsystem.hotel_service.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    boolean existsByName(String name); // Kiểm tra loại phòng theo tên
}