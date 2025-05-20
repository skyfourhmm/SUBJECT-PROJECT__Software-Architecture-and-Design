package com.hotelmanagementsystem.hotel_service.entity;

public enum RoomStatus {
    AVAILABLE,     // Phòng còn trống
    BOOKED,        // Phòng đã được đặt
    OUT_OF_SERVICE // Phòng không sử dụng được (bảo trì, hỏng hóc, v.v.)
}
