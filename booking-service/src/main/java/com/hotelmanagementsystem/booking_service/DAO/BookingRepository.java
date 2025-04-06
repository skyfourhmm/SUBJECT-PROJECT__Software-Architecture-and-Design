package com.hotelmanagementsystem.booking_service.DAO;


import com.hotelmanagementsystem.booking_service.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerId(Long customerId);
}
