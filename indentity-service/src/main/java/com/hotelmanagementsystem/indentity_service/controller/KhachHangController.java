package com.hotelmanagementsystem.indentity_service.controller;

import com.hotelmanagementsystem.indentity_service.dto.KhachHangUpdateDTO;
import com.hotelmanagementsystem.indentity_service.entity.KhachHang;
import com.hotelmanagementsystem.indentity_service.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class KhachHangController {

   @Autowired
   private KhachHangService khachHangService;

   @GetMapping("/list")
   public ResponseEntity<List<KhachHang>> getAllKhachHang() {
       List<KhachHang> danhSachKhachHang = khachHangService.getAllKhachHang();
       return ResponseEntity.ok(danhSachKhachHang);
   }

   @GetMapping("/by-sdt")
    public ResponseEntity<KhachHang> getBySoDienThoai(@RequestParam String soDienThoai) {
        return khachHangService.getBySoDienThoai(soDienThoai)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PutMapping("/me")
//    public ResponseEntity<?> updateOwnInfo(@RequestBody KhachHangUpdateDTO dto,
//                                           @AuthenticationPrincipal UserDetails userDetails) {
//        KhachHang updated = khachHangService.updateByUsername(userDetails.getUsername(), dto);
//        return ResponseEntity.ok(updated);
//    }
}