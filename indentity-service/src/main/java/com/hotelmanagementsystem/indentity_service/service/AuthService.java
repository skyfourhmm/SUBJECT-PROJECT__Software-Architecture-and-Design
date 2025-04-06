package com.hotelmanagementsystem.indentity_service.service;

import com.hotelmanagementsystem.indentity_service.dto.LoginRequest;
import com.hotelmanagementsystem.indentity_service.dto.LoginResponse;
import com.hotelmanagementsystem.indentity_service.dto.RegisterRequest;
import com.hotelmanagementsystem.indentity_service.dto.ResponseDTO;
import com.hotelmanagementsystem.indentity_service.entity.*;
import com.hotelmanagementsystem.indentity_service.repository.KhachHangRepository;
import com.hotelmanagementsystem.indentity_service.repository.LoaiNhanVienRepository;
import com.hotelmanagementsystem.indentity_service.repository.NhanVienRepository;
import com.hotelmanagementsystem.indentity_service.repository.TaiKhoanRepository;
import com.hotelmanagementsystem.indentity_service.security.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private LoaiNhanVienRepository loaiNhanVienRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public ResponseDTO registerUser(RegisterRequest registerRequest) {
        // Kiểm tra xem tất cả các trường có được nhập đầy đủ không
        if (registerRequest.getTenDangNhap() == null || registerRequest.getTenDangNhap().isEmpty()) {
            return new ResponseDTO("Tên đăng nhập không được để trống.", "ERROR");
        }
        if (registerRequest.getMatKhau() == null || registerRequest.getMatKhau().isEmpty()) {
            return new ResponseDTO("Mật khẩu không được để trống.", "ERROR");
        }
        if (registerRequest.getHoTen() == null || registerRequest.getHoTen().isEmpty()) {
            return new ResponseDTO("Họ tên không được để trống.", "ERROR");
        }
        if (registerRequest.getGioiTinh() == null) {
            return new ResponseDTO("Giới tính không được để trống.", "ERROR");
        }
        if (registerRequest.getNgaySinh() == null) {
            return new ResponseDTO("Ngày sinh không được để trống.", "ERROR");
        }
        if (registerRequest.getDiaChi() == null || registerRequest.getDiaChi().isEmpty()) {
            return new ResponseDTO("Địa chỉ không được để trống.", "ERROR");
        }
        if (registerRequest.getSoDienThoai() == null || registerRequest.getSoDienThoai().isEmpty()) {
            return new ResponseDTO("Số điện thoại không được để trống.", "ERROR");
        }

        // Kiểm tra xem tên đăng nhập đã tồn tại chưa
        Optional<TaiKhoan> existingTaiKhoan = taiKhoanRepository.findByTenDangNhap(registerRequest.getTenDangNhap());
        if (existingTaiKhoan.isPresent()) {
            return new ResponseDTO("Tên đăng nhập đã tồn tại.", "ERROR");
        }

        // Hash mật khẩu trước khi lưu
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(registerRequest.getMatKhau());

        // Tạo tài khoản với mật khẩu đã được hash
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenDangNhap(registerRequest.getTenDangNhap());
        taiKhoan.setMatKhau(hashedPassword);
        taiKhoan.setRole(registerRequest.getRole());
        taiKhoan.setTrangThai(true);  // Mặc định tài khoản được kích hoạt

        taiKhoanRepository.save(taiKhoan);

        // Tạo khách hàng hoặc nhân viên tùy theo Role
        if (registerRequest.getRole() == Role.CUSTOMER) {
            // Tạo khách hàng
            KhachHang khachHang = new KhachHang();
            khachHang.setHoTen(registerRequest.getHoTen());
            khachHang.setGioiTinh(registerRequest.getGioiTinh());
            khachHang.setNgaySinh(registerRequest.getNgaySinh());
            khachHang.setDiaChi(registerRequest.getDiaChi());
            khachHang.setSoDienThoai(registerRequest.getSoDienThoai());
            khachHang.setDiemThuong(0);  // Mặc định điểm thưởng là 0
            khachHang.setGhiChu("");  // Không có ghi chú

            khachHang.setTaiKhoan(taiKhoan);

            khachHangRepository.save(khachHang);
        } else if (registerRequest.getRole() == Role.EMPLOYEE || registerRequest.getRole() == Role.OWNER) {

            LoaiNhanVien loaiNhanVien = loaiNhanVienRepository.findByRole(registerRequest.getRole())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy loại nhân viên với role: " + registerRequest.getRole()));


            // Tạo nhân viên
            NhanVien nhanVien = new NhanVien();
            nhanVien.setHoTen(registerRequest.getHoTen());
            nhanVien.setGioiTinh(registerRequest.getGioiTinh());
            nhanVien.setNgaySinh(registerRequest.getNgaySinh());
            nhanVien.setDiaChi(registerRequest.getDiaChi());
            nhanVien.setSoDienThoai(registerRequest.getSoDienThoai());
            nhanVien.setAnhThe("");  // Không có ảnh thẻ lúc đăng ký
            nhanVien.setTaiKhoan(taiKhoan);  // Liên kết tài khoản với nhân viên
            nhanVien.setLoaiNhanVien(loaiNhanVien);  // Đặt loại nhân viên

            // Lưu nhân viên vào cơ sở dữ liệu
            nhanVienRepository.save(nhanVien);
        }

        return new ResponseDTO("Đăng ký thành công!", "SUCCESS");
    }

    public ResponseDTO loginUser(LoginRequest loginRequest) {
        // Kiểm tra tên đăng nhập và mật khẩu
        Optional<TaiKhoan> taiKhoanOpt = taiKhoanRepository.findByTenDangNhap(loginRequest.getTenDangNhap());
        if (!taiKhoanOpt.isPresent()) {
            return new ResponseDTO("Tên đăng nhập không tồn tại.", "ERROR");
        }

        if (loginRequest == null || loginRequest.getMatKhau() == null || loginRequest.getMatKhau().isEmpty()) {
            return new ResponseDTO("Phải có mật khẩu.", "ERROR");
        }

        TaiKhoan taiKhoan = taiKhoanOpt.get();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(loginRequest.getMatKhau(), taiKhoan.getMatKhau())) {
            return new ResponseDTO("Mật khẩu không đúng.", "ERROR");
        }


        // Xử lý tạo JWT token
        String token = jwtUtil.generateToken(taiKhoan);

        return new ResponseDTO("Đăng nhập thành công!", "SUCCESS", token);
    }

}
