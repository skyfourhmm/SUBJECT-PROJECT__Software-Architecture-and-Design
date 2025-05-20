package com.hotelmanagementsystem.indentity_service.service;

import com.hotelmanagementsystem.indentity_service.dto.*;
import com.hotelmanagementsystem.indentity_service.entity.*;
import com.hotelmanagementsystem.indentity_service.repository.KhachHangRepository;
import com.hotelmanagementsystem.indentity_service.repository.NhanVienRepository;
import com.hotelmanagementsystem.indentity_service.repository.TaiKhoanRepository;
import com.hotelmanagementsystem.indentity_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        // 1. Tìm tài khoản theo tenDangNhap
        TaiKhoan taiKhoan = taiKhoanRepository.findByTenDangNhap(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản"));

        // 2. Kiểm tra mật khẩu
        if (!passwordEncoder.matches(request.getPassword(), taiKhoan.getMatKhau())) {
            throw new BadCredentialsException("Sai mật khẩu");
        }

        // 3. Tạo token JWT
        String token = jwtUtil.generateToken(taiKhoan.getTenDangNhap());

        // 4. Tìm Khách hàng hoặc Nhân viên theo maTaiKhoan
        Optional<KhachHang> khachHangOpt = khachHangRepository.findByTaiKhoan(taiKhoan);
        Optional<NhanVien> nhanVienOpt = nhanVienRepository.findByTaiKhoan(taiKhoan);

        Object userInfo = null;
        String role = "UNKNOWN";

        if (khachHangOpt.isPresent()) {
            userInfo = khachHangOpt.get();
            role = "KHACH_HANG";
        } else if (nhanVienOpt.isPresent()) {
            userInfo = nhanVienOpt.get();
            role = "NHAN_VIEN";
        }

        // 5. Trả về token + thông tin người dùng
        return new LoginResponse(token, taiKhoan.getTenDangNhap(), role, userInfo);
    }


    public String register(RegisterRequest request) {
        // 1. Check username
        if (taiKhoanRepository.existsByTenDangNhap(request.getTenDangNhap())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại!");
        }

        // 2. Tạo tài khoản
        TaiKhoan tk = new TaiKhoan();
        tk.setMaTaiKhoan(UUID.randomUUID().toString());
        tk.setTenDangNhap(request.getTenDangNhap());
        tk.setMatKhau(new BCryptPasswordEncoder().encode(request.getMatKhau()));
        tk.setTrangThai("ACTIVE");
        taiKhoanRepository.save(tk);

        // 3. Tạo đối tượng Khách hoặc Nhân viên
        if ("khachHang".equalsIgnoreCase(request.getLoaiNguoiDung())) {
            KhachHang kh = new KhachHang();
            kh.setMaKhachHang(UUID.randomUUID().toString());
            kh.setHoTen(request.getHoTen());
            kh.setGioiTinh(request.getGioiTinh());
            kh.setNgaySinh(request.getNgaySinh());
            kh.setDiaChi(request.getDiaChi());
            kh.setSoDienThoai(request.getSoDienThoai());
            kh.setTaiKhoan(tk);

            khachHangRepository.save(kh);
        } else if ("nhanVien".equalsIgnoreCase(request.getLoaiNguoiDung())) {
            NhanVien nv = new NhanVien();
            nv.setMaNhanVien(UUID.randomUUID().toString());
            nv.setHoTen(request.getHoTen());
            nv.setGioiTinh(request.getGioiTinh());
            nv.setNgaySinh(request.getNgaySinh());
            nv.setDiaChi(request.getDiaChi());
            nv.setSoDienThoai(request.getSoDienThoai());
            nv.setTrangThai("Đang làm");
            nv.setTaiKhoan(tk);

            nhanVienRepository.save(nv);
        } else {
            throw new RuntimeException("Loại người dùng không hợp lệ");
        }

        return "Đăng ký thành công!";
    }



}
