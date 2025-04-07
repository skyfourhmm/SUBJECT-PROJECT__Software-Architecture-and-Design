package com.hotelmanagementsystem.hotel_service.repository;

import com.hotelmanagementsystem.hotel_service.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByHotelId(int hotelId);
}
