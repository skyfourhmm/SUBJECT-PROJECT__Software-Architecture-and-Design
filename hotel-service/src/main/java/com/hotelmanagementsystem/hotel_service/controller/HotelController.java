package com.hotelmanagementsystem.hotel_service.controller;

import com.hotelmanagementsystem.hotel_service.dto.*;
import com.hotelmanagementsystem.hotel_service.service.HotelService;
import com.hotelmanagementsystem.hotel_service.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ReviewService reviewService;

    // Room endpoints
    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDTO>> getHotelRooms() {
        return ResponseEntity.ok(hotelService.getHotelRooms());
    }

    @PostMapping("/rooms")
    public ResponseEntity<RoomDTO> addRoom(@RequestBody CreateRoomDTO dto) {
        return ResponseEntity.ok(hotelService.addRoom(dto));
    }

    @PutMapping("/rooms/{roomId}")
    public ResponseEntity<RoomDTO> updateRoom(
            @PathVariable Integer roomId, @RequestBody UpdateRoomDTO dto) {
        return ResponseEntity.ok(hotelService.updateRoom(roomId, dto));
    }

    @DeleteMapping("/rooms/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Integer roomId) {
        hotelService.deleteRoom(roomId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rooms/{roomId}/amenities")
    public ResponseEntity<List<String>> getRoomAmenities(@PathVariable Integer roomId) {
        return ResponseEntity.ok(hotelService.getRoomAmenities(roomId));
    }

    @PostMapping("/rooms/{roomId}/amenities")
    public ResponseEntity<Void> assignAmenitiesToRoom(
            @PathVariable Integer roomId, @RequestBody List<Integer> amenityIds) {
        hotelService.assignAmenitiesToRoom(roomId, amenityIds);
        return ResponseEntity.ok().build();
    }

    // RoomType endpoints
    @GetMapping("/room-types")
    public ResponseEntity<List<RoomTypeDTO>> getRoomTypes() {
        return ResponseEntity.ok(hotelService.getRoomTypes());
    }

    @PostMapping("/room-types")
    public ResponseEntity<RoomTypeDTO> addRoomType(@RequestBody CreateRoomTypeDTO dto) {
        return ResponseEntity.ok(hotelService.addRoomType(dto));
    }

    @PutMapping("/room-types/{roomTypeId}")
    public ResponseEntity<RoomTypeDTO> updateRoomType(
            @PathVariable Integer roomTypeId, @RequestBody UpdateRoomTypeDTO dto) {
        return ResponseEntity.ok(hotelService.updateRoomType(roomTypeId, dto));
    }

    @DeleteMapping("/room-types/{roomTypeId}")
    public ResponseEntity<Void> deleteRoomType(@PathVariable Integer roomTypeId) {
        hotelService.deleteRoomType(roomTypeId);
        return ResponseEntity.ok().build();
    }

    // Review endpoints
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviews() {
        return ResponseEntity.ok(reviewService.getReviews());
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@RequestBody CreateReviewDTO dto) {
        reviewService.addReview(dto);
        return ResponseEntity.ok("Review added successfully");
    }
}