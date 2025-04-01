package com.hotelmanagementsystem.indentity_service.service;

import com.hotelmanagementsystem.indentity_service.dto.ChangePasswordRequest;
import com.hotelmanagementsystem.indentity_service.entity.TaiKhoan;
import com.hotelmanagementsystem.indentity_service.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private JwtService jwtService; // Dịch vụ để trích xuất tên đăng nhập từ token

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void doiMatKhau(ChangePasswordRequest request, String token) {
        // Lấy tên đăng nhập từ token
        String tenDangNhap = jwtService.extractUsername(token);

        // Tìm tài khoản theo tên đăng nhập
        TaiKhoan taiKhoan = taiKhoanRepository.findById(tenDangNhap)
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));

        // Kiểm tra mật khẩu cũ
        if (!passwordEncoder.matches(request.getOldPassword(), taiKhoan.getMatKhau())) {
            throw new RuntimeException("Mật khẩu cũ không đúng!");
        }

        // Mã hóa và cập nhật mật khẩu mới
        taiKhoan.setMatKhau(passwordEncoder.encode(request.getNewPassword()));
        taiKhoanRepository.save(taiKhoan);
    }
}

