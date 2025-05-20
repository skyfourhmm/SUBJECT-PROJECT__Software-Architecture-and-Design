package com.hotelmanagementsystem.hotel_service.repository;

import com.hotelmanagementsystem.hotel_service.entity.Room;
import com.hotelmanagementsystem.hotel_service.entity.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    // Thêm phương thức tìm phòng theo ID
    Optional<Room> findById(UUID id);
    List<Room> findByStatus(RoomStatus status);  // Tìm phòng theo trạng thái

}