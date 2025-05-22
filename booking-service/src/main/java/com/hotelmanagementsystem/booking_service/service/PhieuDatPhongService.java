package com.hotelmanagementsystem.booking_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.booking_service.entity.PhieuDatPhong;
import com.hotelmanagementsystem.booking_service.repository.PhieuDatPhongRepository;

@Service
public class PhieuDatPhongService {

    private final PhieuDatPhongRepository repository;

    public PhieuDatPhongService(PhieuDatPhongRepository repository) {
        this.repository = repository;
    }

    public PhieuDatPhong save(PhieuDatPhong phieu) {
        phieu.setCreatedAt(java.time.LocalDateTime.now());
        phieu.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(phieu);
    }

    public Optional<PhieuDatPhong> findById(String maPhieuDat) {
        return repository.findById(maPhieuDat);
    }

    public List<PhieuDatPhong> findAll() {
        return repository.findAll();
    }

    public void deleteById(String maPhieuDat) {
        repository.deleteById(maPhieuDat);
    }
}