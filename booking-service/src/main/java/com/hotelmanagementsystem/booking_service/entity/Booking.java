package com.hotelmanagementsystem.booking_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@Data
public class Booking {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID userId; // từ service identity
    private UUID roomId;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @Enumerated(EnumType.STRING)
    private BookingType bookingType; // ONLINE, WALK_IN

    @Enumerated(EnumType.STRING)
    private BookingStatus status; // BOOKED, CANCELED, COMPLETED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters, setters, constructors...
}
