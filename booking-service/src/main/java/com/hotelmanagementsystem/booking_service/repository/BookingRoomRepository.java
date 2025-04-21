package com.hotelmanagementsystem.booking_service.repository;
import com.hotelmanagementsystem.booking_service.entity.BookingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRoomRepository extends JpaRepository<BookingRoom, Long> {
    List<BookingRoom> findByBookingId(Long bookingId);
}
