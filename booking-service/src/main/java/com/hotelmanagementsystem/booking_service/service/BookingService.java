package com.hotelmanagementsystem.booking_service.service;

import com.hotelmanagementsystem.booking_service.dto.BookingRequestDTO;
import com.hotelmanagementsystem.booking_service.dto.BookingResponseDTO;
import com.hotelmanagementsystem.booking_service.dto.PaymentRequestDTO;

import com.hotelmanagementsystem.booking_service.entity.*;
import com.hotelmanagementsystem.booking_service.repository.*;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRoomRepository bookingRoomRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO request) {
        // Kiểm tra ngày
        if (request.getCheckinDate() == null || request.getCheckoutDate() == null) {
            throw new IllegalArgumentException("Check-in and check-out dates must not be null");
        }
        if (request.getCheckinDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Check-in date must not be in the past");
        }
        if (!request.getCheckinDate().isBefore(request.getCheckoutDate())) {
            throw new IllegalArgumentException("Check-in date must be before check-out date");
        }

        // Kiểm tra khách hàng
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + request.getCustomerId() + " not found"));

        // Kiểm tra phòng trống
        List<Room> availableRooms = roomRepository.findAvailableRoomsForDates(
                request.getCheckinDate(), request.getCheckoutDate());
        List<Room> selectedRooms = availableRooms.stream()
                .filter(room -> request.getRoomIds().contains(room.getId()))
                .collect(Collectors.toList());

        if (selectedRooms.size() != request.getRoomIds().size()) {
            throw new IllegalArgumentException("One or more rooms are not available for the selected dates");
        }

        // Kiểm tra bookingType
        String bookingType = request.getBookingType();
        if (bookingType == null) {
            throw new IllegalArgumentException("Booking type must not be null");
        }
        if (!"ONLINE".equals(bookingType) && !"CONFIRMED".equals(bookingType)) {
            throw new IllegalArgumentException("Invalid booking type: " + bookingType);
        }

        // Tạo booking
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setCheckinDate(request.getCheckinDate());
        booking.setCheckoutDate(request.getCheckoutDate());
        booking.setStatus("ONLINE".equals(bookingType) ? BookingStatus.PENDING : BookingStatus.CONFIRMED);
        booking = bookingRepository.save(booking);

        // Liên kết phòng với booking
        for (Room room : selectedRooms) {
            BookingRoom bookingRoom = new BookingRoom();
            bookingRoom.setBooking(booking);
            bookingRoom.setRoom(room);
            bookingRoomRepository.save(bookingRoom);
        }

        // Cập nhật trạng thái phòng
        selectedRooms.forEach(room -> room.setStatus(RoomStatus.BOOKED));
        roomRepository.saveAll(selectedRooms);

        // Trả về response
        BookingResponseDTO response = new BookingResponseDTO();
        response.setId(booking.getId());
        response.setCustomerId(booking.getCustomer().getId());
        response.setCheckinDate(booking.getCheckinDate());
        response.setCheckoutDate(booking.getCheckoutDate());
        response.setStatus(booking.getStatus().name());
        response.setRoomIds(selectedRooms.stream().map(Room::getId).collect(Collectors.toList()));

        return response;
    }

    public List<Room> checkRoomAvailability(LocalDate checkinDate, LocalDate checkoutDate) {
        if (checkinDate == null || checkoutDate == null) {
            throw new IllegalArgumentException("Check-in and check-out dates must not be null");
        }
        if (checkinDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Check-in date must not be in the past");
        }
        if (!checkinDate.isBefore(checkoutDate)) {
            throw new IllegalArgumentException("Check-in date must be before check-out date");
        }

        return roomRepository.findAvailableRoomsForDates(checkinDate, checkoutDate);
    }

    @Transactional
    public Payment processPayment(PaymentRequestDTO request) {
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than 0");
        }

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + request.getBookingId()));

        // Giả lập tương tác với Payment Service
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(request.getAmount());
        payment.setStatus(PaymentStatus.COMPLETED);
        payment = paymentRepository.save(payment);

        // Cập nhật trạng thái booking
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);

        return payment;
    }

    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + bookingId));

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        // Cập nhật trạng thái phòng
        List<BookingRoom> bookingRooms = bookingRoomRepository.findByBookingId(bookingId);
        List<Room> roomsToUpdate = bookingRooms.stream()
                .map(BookingRoom::getRoom)
                .collect(Collectors.toList());
        roomsToUpdate.forEach(room -> room.setStatus(RoomStatus.AVAILABLE));
        roomRepository.saveAll(roomsToUpdate);
    }

    @Transactional
    public BookingResponseDTO updateBooking(Long bookingId, BookingRequestDTO request) {
        // Kiểm tra ngày
        if (request.getCheckinDate() == null || request.getCheckoutDate() == null) {
            throw new IllegalArgumentException("Check-in and check-out dates must not be null");
        }
        if (request.getCheckinDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Check-in date must not be in the past");
        }
        if (!request.getCheckinDate().isBefore(request.getCheckoutDate())) {
            throw new IllegalArgumentException("Check-in date must be before check-out date");
        }

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + bookingId));

        // Hủy các phòng cũ
        List<BookingRoom> oldBookingRooms = bookingRoomRepository.findByBookingId(bookingId);
        List<Room> oldRooms = oldBookingRooms.stream()
                .map(BookingRoom::getRoom)
                .collect(Collectors.toList());
        oldRooms.forEach(room -> room.setStatus(RoomStatus.AVAILABLE));
        roomRepository.saveAll(oldRooms);
        bookingRoomRepository.deleteAll(oldBookingRooms);

        // Kiểm tra phòng mới
        List<Room> availableRooms = roomRepository.findAvailableRoomsForDates(
                request.getCheckinDate(), request.getCheckoutDate());
        List<Room> selectedRooms = availableRooms.stream()
                .filter(room -> request.getRoomIds().contains(room.getId()))
                .collect(Collectors.toList());

        if (selectedRooms.size() != request.getRoomIds().size()) {
            throw new IllegalArgumentException("One or more rooms are not available");
        }

        // Cập nhật thông tin booking
        booking.setCheckinDate(request.getCheckinDate());
        booking.setCheckoutDate(request.getCheckoutDate());
        booking = bookingRepository.save(booking);

        // Liên kết phòng mới
        for (Room room : selectedRooms) {
            BookingRoom bookingRoom = new BookingRoom();
            bookingRoom.setBooking(booking);
            bookingRoom.setRoom(room);
            bookingRoomRepository.save(bookingRoom);
        }

        // Cập nhật trạng thái phòng
        selectedRooms.forEach(room -> room.setStatus(RoomStatus.BOOKED));
        roomRepository.saveAll(selectedRooms);

        // Trả về response
        BookingResponseDTO response = new BookingResponseDTO();
        response.setId(booking.getId());
        response.setCustomerId(booking.getCustomer().getId());
        response.setCheckinDate(booking.getCheckinDate());
        response.setCheckoutDate(booking.getCheckoutDate());
        response.setStatus(booking.getStatus().name());
        response.setRoomIds(selectedRooms.stream().map(Room::getId).collect(Collectors.toList()));
        return response;
    }

    public List<Booking> getBookingHistory(Long customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException("Customer ID must not be null");
        }
        customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + customerId));
        return bookingRepository.findByCustomerId(customerId);
    }
}