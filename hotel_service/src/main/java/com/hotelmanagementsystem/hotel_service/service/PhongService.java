package com.hotelmanagementsystem.hotel_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.hotel_service.dto.PhongRequest;
import com.hotelmanagementsystem.hotel_service.entity.LoaiPhong;
import com.hotelmanagementsystem.hotel_service.entity.Phong;
import com.hotelmanagementsystem.hotel_service.entity.TrangThaiPhong;
import com.hotelmanagementsystem.hotel_service.repository.LoaiPhongRepository;
import com.hotelmanagementsystem.hotel_service.repository.PhongRepository;
import com.hotelmanagementsystem.hotel_service.repository.TrangThaiPhongRepository;

@Service
public class PhongService {

    @Autowired
    private PhongRepository phongRepository;

    @Autowired
    private LoaiPhongRepository loaiPhongRepository;

    @Autowired
    private TrangThaiPhongRepository trangThaiPhongRepository;

    public Phong themPhong(PhongRequest request) {
        Optional<LoaiPhong> loaiPhongOpt = loaiPhongRepository.findById(request.getMaLoaiPhong());
        Optional<TrangThaiPhong> trangThaiOpt = trangThaiPhongRepository.findById(request.getMaTrangThai());

        if (loaiPhongOpt.isEmpty() || trangThaiOpt.isEmpty()) {
            throw new RuntimeException("Loại phòng hoặc trạng thái phòng không tồn tại");
        }

        Phong phong = new Phong();
        phong.setTenPhong(request.getTenPhong());
        phong.setGhiChu(request.getGhiChu());
        phong.setTinhTrangPhong(request.getTinhTrangPhong());
        phong.setLoaiPhong(loaiPhongOpt.get());
        phong.setTrangThaiPhong(trangThaiOpt.get());

        return phongRepository.save(phong);
    }

     public List<Phong> layDanhSachPhongTheoLoai(String maLoaiPhong) {
        return phongRepository.findByLoaiPhong_MaLoaiPhong(maLoaiPhong);
    }

    public Phong layPhongTheoMaPhong(String maPhong) {
        return phongRepository.findById(maPhong).orElse(null);
    }

}