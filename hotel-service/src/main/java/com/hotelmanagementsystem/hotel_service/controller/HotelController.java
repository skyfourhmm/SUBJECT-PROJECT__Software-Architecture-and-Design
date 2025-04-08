package com.hotelmanagementsystem.hotel_service.controller;

import com.hotelmanagementsystem.hotel_service.dto.*;
import com.hotelmanagementsystem.hotel_service.service.HotelService;
import com.hotelmanagementsystem.hotel_service.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@RequestBody CreateHotelDTO dto) {
        HotelDTO hotel = hotelService.createHotel(dto);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelInfo(@PathVariable int id) {
        return ResponseEntity.ok(hotelService.getHotelInfo(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable int id, @RequestBody UpdateHotelDTO dto) {
        HotelDTO updatedHotel = hotelService.updateHotel(id, dto);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable int id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

    @GetMapping("/{id}/rooms")
    public ResponseEntity<List<RoomDTO>> getHotelRooms(@PathVariable int id) {
        return ResponseEntity.ok(hotelService.getHotelRooms(id));
    }

    @GetMapping("/{id}/amenities")
    public ResponseEntity<List<String>> getAmenities(@PathVariable int id) {
        return ResponseEntity.ok(hotelService.getHotelAmenities(id));
    }

    @PostMapping("/{id}/amenities")
    public ResponseEntity<Void> assignAmenitiesToHotel(@PathVariable int id, @RequestBody List<Integer> amenityIds) {
        hotelService.assignAmenitiesToHotel(id, amenityIds);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable int id) {
        return ResponseEntity.ok(reviewService.getReviewsByHotelId(id));
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<String> addReview(@PathVariable int id, @RequestBody CreateReviewDTO dto) {
        reviewService.addReview(id, dto);
        return ResponseEntity.ok("Review added successfully");
    }
}
