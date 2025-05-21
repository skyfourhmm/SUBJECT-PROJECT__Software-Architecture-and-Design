package com.hotelmanagementsystem.hotel_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementsystem.hotel_service.entity.Phong;
import com.hotelmanagementsystem.hotel_service.service.PhongService;

@RestController
@RequestMapping("/api/phong")
public class PhongController {

    private final PhongService phongService;

    public PhongController(PhongService phongService) {
        this.phongService = phongService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Phong>> getAllPhong() {
        List<Phong> danhSachPhong = phongService.getAllPhong();
        return ResponseEntity.ok(danhSachPhong);
    }

     // Lấy phòng theo trạng thái, ví dụ: /api/phong/by-trangthai?tinhTrangPhong=Trống
    @GetMapping("/by-trangthai")
    public ResponseEntity<List<Phong>> getPhongByTrangThai(@RequestParam("tinhTrangPhong") String tinhTrangPhong) {
        List<Phong> phongTheoTrangThai = phongService.getPhongByTrangThai(tinhTrangPhong);
        if (phongTheoTrangThai.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(phongTheoTrangThai);
    }
}