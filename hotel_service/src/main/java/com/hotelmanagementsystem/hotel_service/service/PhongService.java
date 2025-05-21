package com.hotelmanagementsystem.hotel_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.hotel_service.entity.Phong;
import com.hotelmanagementsystem.hotel_service.repository.PhongRepository;

@Service
public class PhongService {

    private final PhongRepository phongRepository;

    public PhongService(PhongRepository phongRepository) {
        this.phongRepository = phongRepository;
    }

    public List<Phong> getAllPhong() {
        return phongRepository.findAll();
    }

     // Lấy phòng theo trạng thái
    public List<Phong> getPhongByTrangThai(String tinhTrangPhong) {
        return phongRepository.findByTrangThaiPhong_TenTrangThai(tinhTrangPhong);
    }
}