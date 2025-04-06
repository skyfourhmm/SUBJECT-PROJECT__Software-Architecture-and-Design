package com.hotelmanagementsystem.booking_service.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookingRequestDTO {
    private Long customerId;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private List<Long> roomIds;
    private String bookingType; // "ONLINE" or "COUNTER"
}
