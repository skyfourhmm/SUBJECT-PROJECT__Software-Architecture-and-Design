package com.hotelmanagementsystem.booking_service.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long hotelId;
    private Double price;
    private String status; // "AVAILABLE", "BOOKED", "MAINTENANCE"
}
