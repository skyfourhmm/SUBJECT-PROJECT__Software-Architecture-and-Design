package com.hotelmanagementsystem.booking_service.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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

    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private String status; // "PENDING", "CONFIRMED", "CANCELLED"
}
