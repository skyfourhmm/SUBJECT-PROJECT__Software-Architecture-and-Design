package com.hotelmanagementsystem.hotel_service.repository;

import com.hotelmanagementsystem.hotel_service.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    boolean existsByNumber(String number); // Kiểm tra phòng theo số phòng
}