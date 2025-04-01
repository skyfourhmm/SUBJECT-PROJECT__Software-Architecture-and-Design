package com.hotelmanagementsystem.indentity_service.service;

import com.hotelmanagementsystem.indentity_service.dto.LoginRequest;
import com.hotelmanagementsystem.indentity_service.dto.LoginResponse;
import com.hotelmanagementsystem.indentity_service.dto.RegisterRequest;
import com.hotelmanagementsystem.indentity_service.entity.KhachHang;
import com.hotelmanagementsystem.indentity_service.entity.Role;
import com.hotelmanagementsystem.indentity_service.entity.TaiKhoan;
import com.hotelmanagementsystem.indentity_service.repository.KhachHangRepository;
import com.hotelmanagementsystem.indentity_service.repository.TaiKhoanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TaiKhoanRepository taiKhoanRepository;
    private final KhachHangRepository khachHangRepository;
    private final PasswordEncoder passwordEncoder; // Để mã hóa mật khẩu
    private final JwtService jwtService;

    @Transactional
    public void register(RegisterRequest request) {
        // Kiểm tra xem tên đăng nhập đã tồn tại chưa
        if (taiKhoanRepository.existsById(request.getTenDangNhap())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại!");
        }

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(request.getMatKhau());

        // Tạo mới tài khoản
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenDangNhap(request.getTenDangNhap());
        taiKhoan.setMatKhau(encodedPassword);
        taiKhoan.setRole(Role.valueOf(request.getRole().toUpperCase()));
        taiKhoan.setTrangThai(true); // Mặc định trạng thái là true (hoạt động)

        // Lưu tài khoản vào cơ sở dữ liệu
        taiKhoanRepository.save(taiKhoan);

        // Tạo mới khách hàng (vì đây là ví dụ cho khách hàng)
        KhachHang khachHang = new KhachHang();
        khachHang.setHoTen(request.getHoTen());
        khachHang.setGioiTinh(request.getGioiTinh());
        khachHang.setNgaySinh(request.getNgaySinh());
        khachHang.setDiaChi(request.getDiaChi());
        khachHang.setSoDienThoai(request.getSoDienThoai());
        khachHang.setDiemThuong(0); // Khởi tạo điểm thưởng là 0
        khachHang.setGhiChu(""); // Ghi chú ban đầu là rỗng


        // Thiết lập quan hệ OneToOne với tài khoản
        khachHang.setTaiKhoan(taiKhoan);


        // Lưu khách hàng vào cơ sở dữ liệu
        khachHangRepository.save(khachHang);
    }

    public LoginResponse login(LoginRequest request) {
        // Tìm tài khoản theo tên đăng nhập
        TaiKhoan taiKhoan = taiKhoanRepository.findById(request.getTenDangNhap())
                .orElseThrow(() -> new RuntimeException("Tên đăng nhập không tồn tại"));

        // Kiểm tra mật khẩu
        if (!passwordEncoder.matches(request.getMatKhau(), taiKhoan.getMatKhau())) {
            throw new RuntimeException("Sai mật khẩu");
        }

        // Tạo JWT token
        String token = jwtService.generateToken(taiKhoan.getTenDangNhap(), taiKhoan.getRole().toString());

        // Trả về thông tin đăng nhập
        return new LoginResponse(token, taiKhoan.getTenDangNhap(), taiKhoan.getRole().toString());
    }
}
