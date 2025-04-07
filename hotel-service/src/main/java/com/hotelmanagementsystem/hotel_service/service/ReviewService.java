package com.hotelmanagementsystem.hotel_service.service;

import com.hotelmanagementsystem.hotel_service.dto.CreateReviewDTO;
import com.hotelmanagementsystem.hotel_service.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getReviewsByHotelId(int hotelId);
    void addReview(int hotelId, CreateReviewDTO dto);
}
