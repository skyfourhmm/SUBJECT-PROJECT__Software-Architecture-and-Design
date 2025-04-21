package com.hotelmanagementsystem.booking_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Hotel ID must not be null")
    private Long hotelId;

    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotNull(message = "Status must not be null")
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<BookingRoom> bookingRooms;
}

