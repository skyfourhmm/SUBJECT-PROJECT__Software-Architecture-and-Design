package com.hotelmanagementsystem.hotel_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementsystem.hotel_service.dto.LoaiPhongRequest;
import com.hotelmanagementsystem.hotel_service.entity.LoaiPhong;
import com.hotelmanagementsystem.hotel_service.service.LoaiPhongService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/loai-phong")
public class LoaiPhongController {

     private final LoaiPhongService loaiPhongService;

    public LoaiPhongController(LoaiPhongService loaiPhongService) {
        this.loaiPhongService = loaiPhongService;
    }

    // API thêm loại phòng, nhận vào LoaiPhongRequest
    @PostMapping("/them")
    public ResponseEntity<LoaiPhong> themLoaiPhong(@RequestBody LoaiPhongRequest request, HttpServletRequest httpRequest) {
        
        String userName = httpRequest.getHeader("X-User-Id");
        System.out.println("User ID: " + userName);
        
        LoaiPhong loaiPhongMoi = loaiPhongService.themLoaiPhong(request);
        System.out.println("Loai phong moi: " + loaiPhongMoi);
        return ResponseEntity.ok(loaiPhongMoi);
    }

    // API lấy danh sách tất cả loại phòng
    @GetMapping("/tat-ca")
    public ResponseEntity<List<LoaiPhong>> getAllLoaiPhong() {
        List<LoaiPhong> danhSach = loaiPhongService.getAllLoaiPhong();
        return ResponseEntity.ok(danhSach);
    }

    
}
