package com.hotelmanagementsystem.booking_service.DTO;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private Long bookingId;
    private Double amount;
}
