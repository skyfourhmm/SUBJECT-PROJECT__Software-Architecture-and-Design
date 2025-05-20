package com.hotelmanagementsystem.hotel_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;  // UUID của phòng
    private String roomNumber; // Số phòng
    private String roomType; // Loại phòng (ví dụ: Single, Double, Suite)

    @Enumerated(EnumType.STRING)  // Đảm bảo trạng thái phòng được lưu dưới dạng chuỗi
    private RoomStatus status;   // Trạng thái phòng (AVAILABLE, BOOKED, OUT_OF_SERVICE)
}