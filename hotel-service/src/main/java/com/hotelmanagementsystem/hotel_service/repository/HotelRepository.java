package com.hotelmanagementsystem.hotel_service.repository;

import com.hotelmanagementsystem.hotel_service.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}