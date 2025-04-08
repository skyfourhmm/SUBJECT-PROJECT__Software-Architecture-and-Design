package com.hotelmanagementsystem.booking_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookingRequestDTO {
    private Long customerId;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private List<Long> roomIds;

    @NotNull(message = "Booking type must not be null")
    private String bookingType;
}
