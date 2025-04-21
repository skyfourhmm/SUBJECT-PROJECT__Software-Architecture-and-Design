package com.hotelmanagementsystem.booking_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "booking")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull(message = "Check-in date must not be null")
    private LocalDate checkinDate;

    @NotNull(message = "Check-out date must not be null")
    private LocalDate checkoutDate;

    @NotNull(message = "Status must not be null")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingRoom> bookingRooms;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;
}

