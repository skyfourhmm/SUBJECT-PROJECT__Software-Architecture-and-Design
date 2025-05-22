package com.hotelmanagementsystem.booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmanagementsystem.booking_service.entity.PhieuDatPhong;

@Repository
public interface PhieuDatPhongRepository extends JpaRepository<PhieuDatPhong, String> {
}