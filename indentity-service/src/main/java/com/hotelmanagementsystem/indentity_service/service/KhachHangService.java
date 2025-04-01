package com.hotelmanagementsystem.indentity_service.service;

import com.hotelmanagementsystem.indentity_service.entity.KhachHang;
import com.hotelmanagementsystem.indentity_service.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }
}
