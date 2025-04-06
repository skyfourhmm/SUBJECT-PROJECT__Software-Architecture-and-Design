package com.hotelmanagementsystem.booking_service.DAO;

import com.hotelmanagementsystem.booking_service.Entity.BookingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRoomRepository extends JpaRepository<BookingRoom, Long> {
}
