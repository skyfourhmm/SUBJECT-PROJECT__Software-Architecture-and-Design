package com.hotelmanagementsystem.hotel_service.repository;

import com.hotelmanagementsystem.hotel_service.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity, Integer> {

}