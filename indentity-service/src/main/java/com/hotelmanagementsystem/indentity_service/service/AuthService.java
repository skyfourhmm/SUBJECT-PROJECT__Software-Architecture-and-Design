package com.hotelmanagementsystem.indentity_service.service;

import com.hotelmanagementsystem.indentity_service.dto.*;
import com.hotelmanagementsystem.indentity_service.entity.*;
import com.hotelmanagementsystem.indentity_service.repository.KhachHangRepository;
import com.hotelmanagementsystem.indentity_service.repository.NhanVienRepository;
import com.hotelmanagementsystem.indentity_service.repository.TaiKhoanRepository;
import com.hotelmanagementsystem.indentity_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
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


    public ApiResponse  register(RegisterRequest request) {
    // 1. Tạo tên đăng nhập nếu chưa nhập
    String tenDangNhap = request.getTenDangNhap();
    if (tenDangNhap == null || tenDangNhap.trim().isEmpty()) {
        tenDangNhap = generateUsernameFromName(request.getHoTen());
    }

    String cccd = request.getCccd();
    if (cccd != null && cccd.length() >= 6) {
        String dau3 = cccd.substring(0, 3);
        String cuoi3 = cccd.substring(cccd.length() - 3);
        String sixDigits = dau3 + cuoi3;
        tenDangNhap = tenDangNhap + sixDigits;  // Nối 6 ký tự vào cuối tên đăng nhập
    } else {
        // Xử lý nếu CCCD không đủ dài (tuỳ bạn)
    }

    // Kiểm tra mật khẩu phải >= 6 ký tự
    String matKhau = request.getMatKhau();
    if (matKhau == null || matKhau.length() < 6) {
        throw new RuntimeException("Mật khẩu phải có ít nhất 6 ký tự!");
    }

    // 2. Kiểm tra tên đăng nhập đã tồn tại
    if (taiKhoanRepository.existsByTenDangNhap(tenDangNhap)) {
        throw new RuntimeException("Tên đăng nhập đã tồn tại!");
    }

    // 3. Kiểm tra trùng số điện thoại và CCCD (chỉ áp dụng cho khách hàng)
    if ("khachHang".equalsIgnoreCase(request.getLoaiNguoiDung())) {
        if (!isValidCCCD(request.getCccd())) {
            throw new RuntimeException("Số CCCD không hợp lệ!");
        }
        if (!request.getSoDienThoai().matches("^0[1-9]\\d{8}$")) {
            throw new RuntimeException("Số điện thoại không hợp lệ! Vui lòng nhập số bắt đầu từ 01 đến 09 và đủ 10 chữ số.");
        }
        if (khachHangRepository.existsBySoDienThoai(request.getSoDienThoai())) {
            throw new RuntimeException("Số điện thoại đã được sử dụng!");
        }
        if (khachHangRepository.existsByCCCD(request.getCccd())) {
            throw new RuntimeException("Số CCCD đã được sử dụng!");
        }
    }

    // 4. Tạo tài khoản
    TaiKhoan tk = new TaiKhoan();
    tk.setMaTaiKhoan(UUID.randomUUID().toString());
    tk.setTenDangNhap(tenDangNhap);
    tk.setMatKhau(new BCryptPasswordEncoder().encode(matKhau));
    tk.setTrangThai("ACTIVE");
    taiKhoanRepository.save(tk);

    // 5. Tạo người dùng tương ứng
    if ("khachHang".equalsIgnoreCase(request.getLoaiNguoiDung())) {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang(UUID.randomUUID().toString());
        kh.setHoTen(request.getHoTen());
        kh.setGioiTinh(request.getGioiTinh());
        kh.setNgaySinh(request.getNgaySinh());
        kh.setDiaChi(request.getDiaChi());
        kh.setSoDienThoai(request.getSoDienThoai());
        kh.setCCCD(request.getCccd());
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

    return new ApiResponse("Đăng ký thành công!");
}



    private String generateUsernameFromName(String hoTen) {
        String normalized = Normalizer.normalize(hoTen, Normalizer.Form.NFD)
                                   .replaceAll("\\p{M}", "")      // Xoá dấu
                                   .toLowerCase()
                                   .replaceAll("[^a-z0-9\\s]", "") // Xoá ký tự đặc biệt
                                   .trim()
                                   .replaceAll("\\s+", "_");       // Thay khoảng trắng bằng _
        return normalized;
    }

    private boolean isValidCCCD(String cccd) {
    // Kiểm tra không null và độ dài đúng 12 ký tự
    if (cccd == null || !cccd.matches("\\d{12}")) {
        return false;
    }
    // Có thể thêm các quy tắc kiểm tra phức tạp hơn ở đây nếu cần
    return true;
}


}
