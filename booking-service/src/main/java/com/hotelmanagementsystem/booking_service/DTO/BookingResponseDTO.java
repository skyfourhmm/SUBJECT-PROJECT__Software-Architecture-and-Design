package com.hotelmanagementsystem.booking_service.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookingResponseDTO {
    private Long id;
    private Long customerId;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private String status;
    private List<Long> roomIds;
}