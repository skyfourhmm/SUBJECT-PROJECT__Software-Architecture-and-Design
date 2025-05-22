package com.hotelmanagementsystem.booking_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.booking_service.dto.PhieuDatPhongRequest;
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

    public Optional<PhieuDatPhong> update(String maPhieuDat, PhieuDatPhongRequest request) {
        Optional<PhieuDatPhong> optional = repository.findById(maPhieuDat);

        if (optional.isPresent()) {
            PhieuDatPhong existing = optional.get();
            existing.setMaKhachHang(request.getMaKhachHang());
            existing.setMaPhong(request.getMaPhong());
            existing.setMaHoaDon(request.getMaHoaDon());
            existing.setCheckIn(request.getCheckIn());
            existing.setCheckOut(request.getCheckOut());
            existing.setTienCoc(request.getTienCoc());
            existing.setTrangThai(request.getTrangThai());
            existing.setMoTa(request.getMoTa());
            existing.setUpdatedAt(LocalDateTime.now());

            repository.save(existing);
            return Optional.of(existing);
        }

        return Optional.empty();
    }
}