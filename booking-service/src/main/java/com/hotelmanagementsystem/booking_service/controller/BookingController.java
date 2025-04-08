package com.hotelmanagementsystem.booking_service.controller;

import com.hotelmanagementsystem.booking_service.dto.BookingRequestDTO;
import com.hotelmanagementsystem.booking_service.dto.BookingResponseDTO;
import com.hotelmanagementsystem.booking_service.dto.PaymentRequestDTO;

import com.hotelmanagementsystem.booking_service.entity.Booking;
import com.hotelmanagementsystem.booking_service.entity.Payment;
import com.hotelmanagementsystem.booking_service.entity.Room;

import com.hotelmanagementsystem.booking_service.service.BookingService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Đặt phòng
    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingRequestDTO request) {
        try {
            BookingResponseDTO response = bookingService.createBooking(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create booking: " + e.getMessage());
        }
    }

    // Kiểm tra tình trạng phòng
    @GetMapping("/rooms/availability")
    public ResponseEntity<List<Room>> checkRoomAvailability(
            @RequestParam LocalDate checkinDate,
            @RequestParam LocalDate checkoutDate) {
        List<Room> rooms = bookingService.checkRoomAvailability(checkinDate, checkoutDate);
        return ResponseEntity.ok(rooms);
    }

    // Thanh toán
    @PostMapping("/payment")
    public ResponseEntity<Payment> processPayment(@Valid @RequestBody PaymentRequestDTO request) {
        Payment payment = bookingService.processPayment(request);
        return ResponseEntity.ok(payment);
    }

    // Hủy đặt phòng
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok().build();
    }

    // Sửa đổi đặt phòng
    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> updateBooking(
            @PathVariable Long id,
            @Valid @RequestBody BookingRequestDTO request) {
        BookingResponseDTO response = bookingService.updateBooking(id, request);
        return ResponseEntity.ok(response);
    }

    // Lịch sử đặt phòng
    @GetMapping("/history/{customerId}")
    public ResponseEntity<List<Booking>> getBookingHistory(@PathVariable Long customerId) {
        List<Booking> bookings = bookingService.getBookingHistory(customerId);
        return ResponseEntity.ok(bookings);
    }

    // Xử lý ngoại lệ
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}