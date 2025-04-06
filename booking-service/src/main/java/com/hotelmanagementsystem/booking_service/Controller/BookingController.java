package com.hotelmanagementsystem.booking_service.Controller;

import com.hotelmanagementsystem.booking_service.DTO.BookingRequestDTO;
import com.hotelmanagementsystem.booking_service.DTO.BookingResponseDTO;
import com.hotelmanagementsystem.booking_service.DTO.PaymentRequestDTO;

import com.hotelmanagementsystem.booking_service.Entity.Booking;
import com.hotelmanagementsystem.booking_service.Entity.Payment;
import com.hotelmanagementsystem.booking_service.Entity.Room;

import com.hotelmanagementsystem.booking_service.Service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO request) {
        BookingResponseDTO response = bookingService.createBooking(request);
        return ResponseEntity.ok(response);
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
    public ResponseEntity<Payment> processPayment(@RequestBody PaymentRequestDTO request) {
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
            @RequestBody BookingRequestDTO request) {
        BookingResponseDTO response = bookingService.updateBooking(id, request);
        return ResponseEntity.ok(response);
    }

    // Lịch sử đặt phòng
    @GetMapping("/history/{customerId}")
    public ResponseEntity<List<Booking>> getBookingHistory(@PathVariable Long customerId) {
        List<Booking> bookings = bookingService.getBookingHistory(customerId);
        return ResponseEntity.ok(bookings);
    }
}
