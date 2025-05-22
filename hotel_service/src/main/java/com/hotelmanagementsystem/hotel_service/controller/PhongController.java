package com.hotelmanagementsystem.hotel_service.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementsystem.hotel_service.dto.PhongRequest;
import com.hotelmanagementsystem.hotel_service.entity.Phong;
import com.hotelmanagementsystem.hotel_service.service.PhongService;

@RestController
@RequestMapping("/api/phong")
public class PhongController {

     @Autowired
    private PhongService phongService;

    @PostMapping("/them")
    public ResponseEntity<Phong> themPhong(@RequestBody PhongRequest request) {
        Phong phongMoi = phongService.themPhong(request);
        return ResponseEntity.ok(phongMoi);
    }

    @GetMapping("/loai/{maLoaiPhong}")
    public ResponseEntity<List<Phong>> layDanhSachPhongTheoLoai(@PathVariable String maLoaiPhong) {
        List<Phong> danhSachPhong = phongService.layDanhSachPhongTheoLoai(maLoaiPhong);
        return ResponseEntity.ok(danhSachPhong);
    }

     @GetMapping("/by-id")
    public ResponseEntity<Phong> layPhongTheoId(@RequestParam String id) {
        Phong phong = phongService.layPhongTheoMaPhong(id);
        if (phong != null) {
            return ResponseEntity.ok(phong);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}