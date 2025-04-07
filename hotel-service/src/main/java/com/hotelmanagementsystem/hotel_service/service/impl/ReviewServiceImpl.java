package com.hotelmanagementsystem.hotel_service.service.impl;

import com.hotelmanagementsystem.hotel_service.dto.CreateReviewDTO;
import com.hotelmanagementsystem.hotel_service.dto.ReviewDTO;
import com.hotelmanagementsystem.hotel_service.entity.Hotel;
import com.hotelmanagementsystem.hotel_service.entity.Review;
import com.hotelmanagementsystem.hotel_service.repository.HotelRepository;
import com.hotelmanagementsystem.hotel_service.repository.ReviewRepository;
import com.hotelmanagementsystem.hotel_service.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private HotelRepository hotelRepo;

    @Override
    public List<ReviewDTO> getReviewsByHotelId(int hotelId) {
        return reviewRepo.findByHotelId(hotelId)
                .stream()
                .map(review -> {
                    ReviewDTO dto = new ReviewDTO();
                    dto.setUsername(review.getUsername());
                    dto.setRating(review.getRating());
                    dto.setComment(review.getComment());
                    dto.setCreatedAt(review.getCreatedAt());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public void addReview(int hotelId, CreateReviewDTO dto) {
        Hotel hotel = hotelRepo.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        Review review = new Review();
        review.setHotel(hotel);
        review.setUsername(dto.getUsername());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        reviewRepo.save(review);
    }
}
