package com.hotelmanagementsystem.booking_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementsystem.booking_service.dto.PhieuDatPhongRequest;
import com.hotelmanagementsystem.booking_service.entity.PhieuDatPhong;
import com.hotelmanagementsystem.booking_service.service.PhieuDatPhongService;

@RestController
@RequestMapping("/api/phieu-dat-phong")
public class PhieuDatPhongController {

    private final PhieuDatPhongService service;

    public PhieuDatPhongController(PhieuDatPhongService service) {
        this.service = service;
    }

    // Tạo mới phiếu đặt phòng
    @PostMapping
    public ResponseEntity<PhieuDatPhong> createPhieuDatPhong(@RequestBody PhieuDatPhong phieu) {
        if (phieu.getMaPhieuDat() == null || phieu.getMaPhieuDat().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        PhieuDatPhong saved = service.save(phieu);
        return ResponseEntity.ok(saved);
    }

    // Lấy tất cả phiếu đặt phòng
    @GetMapping
    public ResponseEntity<List<PhieuDatPhong>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // Lấy phiếu đặt phòng theo id
    @GetMapping("/{id}")
    public ResponseEntity<PhieuDatPhong> getById(@PathVariable("id") String id) {
        Optional<PhieuDatPhong> phieu = service.findById(id);
        return phieu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Xóa phiếu đặt phòng theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{maPhieuDat}")
    public ResponseEntity<?> updatePhieuDatPhong(@PathVariable String maPhieuDat,
                                                 @RequestBody PhieuDatPhongRequest request) {
        return service.update(maPhieuDat, request)
                .map(updated -> ResponseEntity.ok(updated))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}