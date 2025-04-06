package com.hotelmanagementsystem.booking_service.DAO;

import com.hotelmanagementsystem.booking_service.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByStatus(String status);

    @Query("SELECT r FROM Room r WHERE r.status = 'AVAILABLE' AND r.id NOT IN " +
            "(SELECT br.room.id FROM BookingRoom br WHERE br.booking.checkinDate <= :checkoutDate " +
            "AND br.booking.checkoutDate >= :checkinDate AND br.booking.status != 'CANCELLED')")
    List<Room> findAvailableRoomsForDates(LocalDate checkinDate, LocalDate checkoutDate);
}
