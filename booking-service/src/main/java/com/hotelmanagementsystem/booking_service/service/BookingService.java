package com.hotelmanagementsystem.booking_service.service;

import com.hotelmanagementsystem.booking_service.dto.BookingRequestDTO;
import com.hotelmanagementsystem.booking_service.dto.BookingResponseDTO;
import com.hotelmanagementsystem.booking_service.dto.PaymentRequestDTO;

import com.hotelmanagementsystem.booking_service.entity.*;
import com.hotelmanagementsystem.booking_service.repository.*;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(UUID id, Booking booking) {
        booking.setId(id);
        return bookingRepository.save(booking);
    }

    public void cancelBooking(UUID id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> getBookingsByUserId(UUID userId) {
        return bookingRepository.findAllByUserId(userId);
    }
}
