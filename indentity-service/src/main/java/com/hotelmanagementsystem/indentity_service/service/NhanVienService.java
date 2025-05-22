package com.hotelmanagementsystem.indentity_service.service;

import com.hotelmanagementsystem.indentity_service.entity.NhanVien;
import com.hotelmanagementsystem.indentity_service.repository.NhanVienRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    public Optional<NhanVien> getNhanVienById(String maNhanVien) {
        return nhanVienRepository.findById(maNhanVien);
    }
}