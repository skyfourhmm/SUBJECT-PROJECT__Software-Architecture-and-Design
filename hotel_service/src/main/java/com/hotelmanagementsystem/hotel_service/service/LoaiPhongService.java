package com.hotelmanagementsystem.hotel_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.hotel_service.dto.LoaiPhongRequest;
import com.hotelmanagementsystem.hotel_service.entity.LoaiPhong;
import com.hotelmanagementsystem.hotel_service.repository.LoaiPhongRepository;


@Service
public class LoaiPhongService {

    private final LoaiPhongRepository loaiPhongRepository;

    public LoaiPhongService(LoaiPhongRepository loaiPhongRepository) {
        this.loaiPhongRepository = loaiPhongRepository;
    }

    // Thêm loại phòng
    public LoaiPhong themLoaiPhong(LoaiPhongRequest request) {
        LoaiPhong loaiPhong = LoaiPhong.builder()
                .maLoaiPhong(UUID.randomUUID().toString())
                .tenLoaiPhong(request.getTenLoaiPhong())
                .dienTich(request.getDienTich())
                .tienNghi(request.getTienNghi())
                .doiTuongSuDung(request.getDoiTuongSuDung())
                .hinhAnh(request.getHinhAnh())
                .giaPhongTheoDem(request.getGiaPhongTheoDem())
                .giaPhongTheoGio(request.getGiaPhongTheoGio())
                .build();
        return loaiPhongRepository.save(loaiPhong);
    }

    // Lấy danh sách tất cả loại phòng
    public List<LoaiPhong> getAllLoaiPhong() {
        return loaiPhongRepository.findAll();
    }
}