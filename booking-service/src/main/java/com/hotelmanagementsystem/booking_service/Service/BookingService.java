package com.hotelmanagementsystem.booking_service.Service;

import com.hotelmanagementsystem.booking_service.DTO.BookingRequestDTO;
import com.hotelmanagementsystem.booking_service.DTO.BookingResponseDTO;
import com.hotelmanagementsystem.booking_service.DTO.PaymentRequestDTO;

import com.hotelmanagementsystem.booking_service.Entity.*;
import com.hotelmanagementsystem.booking_service.DAO.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

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

    // Đặt phòng
    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO request) {
        // Kiểm tra khách hàng
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Kiểm tra phòng trống
        List<Room> availableRooms = roomRepository.findAvailableRoomsForDates(
                request.getCheckinDate(), request.getCheckoutDate());
        List<Room> selectedRooms = availableRooms.stream()
                .filter(room -> request.getRoomIds().contains(room.getId()))
                .collect(Collectors.toList());

        if (selectedRooms.size() != request.getRoomIds().size()) {
            throw new RuntimeException("One or more rooms are not available");
        }

        // Tạo booking
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setCheckinDate(request.getCheckinDate());
        booking.setCheckoutDate(request.getCheckoutDate());
        booking.setStatus(request.getBookingType().equals("ONLINE") ? "PENDING" : "CONFIRMED");
        booking = bookingRepository.save(booking);

        // Liên kết phòng với booking
        for (Room room : selectedRooms) {
            BookingRoom bookingRoom = new BookingRoom();
            bookingRoom.setBooking(booking);
            bookingRoom.setRoom(room);
            bookingRoomRepository.save(bookingRoom);

            // Cập nhật trạng thái phòng
            room.setStatus("BOOKED");
            roomRepository.save(room);
        }

        // Trả về response
        BookingResponseDTO response = new BookingResponseDTO();
        response.setId(booking.getId());
        response.setCustomerId(booking.getCustomer().getId());
        response.setCheckinDate(booking.getCheckinDate());
        response.setCheckoutDate(booking.getCheckoutDate());
        response.setStatus(booking.getStatus());
        response.setRoomIds(selectedRooms.stream().map(Room::getId).collect(Collectors.toList()));
        return response;
    }

    // Kiểm tra tình trạng phòng
    public List<Room> checkRoomAvailability(LocalDate checkinDate, LocalDate checkoutDate) {
        return roomRepository.findAvailableRoomsForDates(checkinDate, checkoutDate);
    }

    // Thanh toán
    @Transactional
    public Payment processPayment(PaymentRequestDTO request) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Giả lập tương tác với Payment Service (có thể dùng Feign Client hoặc RestTemplate)
        // Ở đây tôi giả định thanh toán thành công
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(request.getAmount());
        payment.setStatus("COMPLETED");
        payment = paymentRepository.save(payment);

        // Cập nhật trạng thái booking
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);

        return payment;
    }

    // Hủy đặt phòng
    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);

        // Cập nhật trạng thái phòng
        List<BookingRoom> bookingRooms = bookingRoomRepository.findAll().stream()
                .filter(br -> br.getBooking().getId().equals(bookingId))
                .collect(Collectors.toList());
        for (BookingRoom br : bookingRooms) {
            Room room = br.getRoom();
            room.setStatus("AVAILABLE");
            roomRepository.save(room);
        }
    }

    // Sửa đổi đặt phòng
    @Transactional
    public BookingResponseDTO updateBooking(Long bookingId, BookingRequestDTO request) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Hủy các phòng cũ
        List<BookingRoom> oldBookingRooms = bookingRoomRepository.findAll().stream()
                .filter(br -> br.getBooking().getId().equals(bookingId))
                .collect(Collectors.toList());
        for (BookingRoom br : oldBookingRooms) {
            Room room = br.getRoom();
            room.setStatus("AVAILABLE");
            roomRepository.save(room);
            bookingRoomRepository.delete(br);
        }

        // Kiểm tra phòng mới
        List<Room> availableRooms = roomRepository.findAvailableRoomsForDates(
                request.getCheckinDate(), request.getCheckoutDate());
        List<Room> selectedRooms = availableRooms.stream()
                .filter(room -> request.getRoomIds().contains(room.getId()))
                .collect(Collectors.toList());

        if (selectedRooms.size() != request.getRoomIds().size()) {
            throw new RuntimeException("One or more rooms are not available");
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

            room.setStatus("BOOKED");
            roomRepository.save(room);
        }

        // Trả về response
        BookingResponseDTO response = new BookingResponseDTO();
        response.setId(booking.getId());
        response.setCustomerId(booking.getCustomer().getId());
        response.setCheckinDate(booking.getCheckinDate());
        response.setCheckoutDate(booking.getCheckoutDate());
        response.setStatus(booking.getStatus());
        response.setRoomIds(selectedRooms.stream().map(Room::getId).collect(Collectors.toList()));
        return response;
    }

    // Lịch sử đặt phòng
    public List<Booking> getBookingHistory(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }
}
