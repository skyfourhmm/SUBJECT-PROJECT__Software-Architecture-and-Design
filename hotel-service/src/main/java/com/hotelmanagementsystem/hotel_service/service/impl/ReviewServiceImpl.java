package com.hotelmanagementsystem.hotel_service.service.impl;

import com.hotelmanagementsystem.hotel_service.dto.CreateReviewDTO;
import com.hotelmanagementsystem.hotel_service.dto.ReviewDTO;
import com.hotelmanagementsystem.hotel_service.entity.Review;
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

    @Override
    public List<ReviewDTO> getReviews() {
        return reviewRepo.findAll()
                .stream()
                .map(review -> {
                    ReviewDTO dto = new ReviewDTO();
                    dto.setUsername(review.getUsername());
                    dto.setRating(review.getRating());
                    dto.setComment(review.getComment());
                    dto.setCreatedAt(review.getCreatedAt());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addReview(CreateReviewDTO dto) {
        Review review = new Review();
        review.setUsername(dto.getUsername());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        reviewRepo.save(review);
    }
}