package com.hotelmanagementsystem.booking_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @NotNull(message = "Amount must not be null")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotNull(message = "Status must not be null")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}

