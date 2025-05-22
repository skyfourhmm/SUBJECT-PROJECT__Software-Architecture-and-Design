package com.hotelmanagementsystem.Payment_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hotelmanagementsystem.Payment_service.entity.HoaDon;
import com.hotelmanagementsystem.Payment_service.service.HoaDonService;

@RestController
@RequestMapping("/api/hoa-don")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    // API tạo hóa đơn
    @PostMapping("/tao")
    public ResponseEntity<HoaDon> taoHoaDon(@RequestBody HoaDon hoaDon) {
        HoaDon hoaDonMoi = hoaDonService.taoHoaDon(hoaDon);
        return ResponseEntity.ok(hoaDonMoi);
    }

    // API cập nhật hóa đơn theo mã
    @PutMapping("/cap-nhat/{maHoaDon}")
    public ResponseEntity<HoaDon> capNhatHoaDon(@PathVariable String maHoaDon, @RequestBody HoaDon hoaDonCapNhat) {
        HoaDon hoaDon = hoaDonService.capNhatHoaDon(maHoaDon, hoaDonCapNhat);
        return ResponseEntity.ok(hoaDon);
    }
}
