package com.hotelmanagementsystem.indentity_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementsystem.indentity_service.entity.NhanVien;
import com.hotelmanagementsystem.indentity_service.service.NhanVienService;

@RestController
@RequestMapping("/api/nhanvien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/{maNhanVien}")
    public ResponseEntity<NhanVien> getNhanVienById(@PathVariable String maNhanVien) {
        return nhanVienService.getNhanVienById(maNhanVien)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
