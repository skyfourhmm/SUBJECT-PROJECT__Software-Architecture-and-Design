//package com.hotelmanagementsystem.indentity_service.service;
//
//import com.hotelmanagementsystem.indentity_service.dto.KhachHangUpdateDTO;
//import com.hotelmanagementsystem.indentity_service.entity.KhachHang;
//import com.hotelmanagementsystem.indentity_service.entity.TaiKhoan;
//import com.hotelmanagementsystem.indentity_service.repository.KhachHangRepository;
//import com.hotelmanagementsystem.indentity_service.repository.TaiKhoanRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class KhachHangService {
//    @Autowired
//    private KhachHangRepository khachHangRepository;
//    @Autowired
//    private TaiKhoanRepository taiKhoanRepository;
//
//    public List<KhachHang> getAllKhachHang() {
//        return khachHangRepository.findAll();
//    }
//
//    public KhachHang updateByUsername(String tenDangNhap, KhachHangUpdateDTO dto) {
//        TaiKhoan taiKhoan = taiKhoanRepository.findByTenDangNhap(tenDangNhap)
//                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));
//
//        KhachHang khachHang = khachHangRepository.findByTaiKhoan(taiKhoan)
//                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));
//
//        khachHang.setHoTen(dto.getHoTen());
//        khachHang.setGioiTinh(dto.getGioiTinh());
//        khachHang.setNgaySinh(dto.getNgaySinh());
//        khachHang.setDiaChi(dto.getDiaChi());
//        khachHang.setSoDienThoai(dto.getSoDienThoai());
//        khachHang.setDiemThuong(dto.getDiemThuong());
//        khachHang.setGhiChu(dto.getGhiChu());
//
//        return khachHangRepository.save(khachHang);
//    }
//
//}
