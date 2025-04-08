package com.hotelmanagementsystem.booking_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "booking_room")
@Data
public class BookingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
}