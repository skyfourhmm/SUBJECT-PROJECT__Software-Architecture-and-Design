package com.hotelmanagementsystem.hotel_service.controller;

import com.hotelmanagementsystem.hotel_service.dto.CreateReviewDTO;
import com.hotelmanagementsystem.hotel_service.dto.HotelDTO;
import com.hotelmanagementsystem.hotel_service.dto.ReviewDTO;
import com.hotelmanagementsystem.hotel_service.dto.RoomDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelInfo(@PathVariable int id) {
        return ResponseEntity.ok(hotelService.getHotelInfo(id));
    }

    @GetMapping("/{id}/rooms")
    public ResponseEntity<List<RoomDTO>> getHotelRooms(@PathVariable int id) {
        return ResponseEntity.ok(hotelService.getHotelRooms(id));
    }

    @GetMapping("/{id}/amenities")
    public ResponseEntity<List<String>> getAmenities(@PathVariable int id) {
        return ResponseEntity.ok(hotelService.getHotelAmenities(id));
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
