package com.hotelmanagementsystem.Payment_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.Payment_service.entity.HoaDon;
import com.hotelmanagementsystem.Payment_service.repository.HoaDonRepository;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    // Tạo mới hóa đơn
    public HoaDon taoHoaDon(HoaDon hoaDon) {
        hoaDon.setMaHoaDon(java.util.UUID.randomUUID().toString()); // tạo mã mới
        hoaDon.setThoiGianTao(LocalDateTime.now()); // set thời gian tạo hiện tại
        return hoaDonRepository.save(hoaDon);
    }

    // Cập nhật hóa đơn (cập nhật theo maHoaDon)
    public HoaDon capNhatHoaDon(String maHoaDon, HoaDon hoaDonCapNhat) {
        Optional<HoaDon> existingHoaDon = hoaDonRepository.findById(maHoaDon);
        if (existingHoaDon.isPresent()) {
            HoaDon hoaDon = existingHoaDon.get();
            // Cập nhật các trường cần thiết, ví dụ:
            hoaDon.setTrangThai(hoaDonCapNhat.getTrangThai());
            hoaDon.setThoiGianThanhToan(hoaDonCapNhat.getThoiGianThanhToan());
            hoaDon.setGiaTien(hoaDonCapNhat.getGiaTien());
            // ... bạn có thể cập nhật thêm các trường khác tùy ý
            return hoaDonRepository.save(hoaDon);
        } else {
            throw new RuntimeException("Không tìm thấy hóa đơn với mã: " + maHoaDon);
        }
    }

     public List<HoaDon> getAllHoaDons() {
        return hoaDonRepository.findAll();
    }
}
