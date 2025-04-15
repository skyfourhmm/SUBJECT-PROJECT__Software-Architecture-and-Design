package com.hotelmanagementsystem.indentity_service;

import com.hotelmanagementsystem.indentity_service.entity.LoaiNhanVien;
import com.hotelmanagementsystem.indentity_service.entity.Role;
import com.hotelmanagementsystem.indentity_service.repository.LoaiNhanVienRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private final LoaiNhanVienRepository loaiNhanVienRepository;

    public DataInitializer(LoaiNhanVienRepository loaiNhanVienRepository) {
        this.loaiNhanVienRepository = loaiNhanVienRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Kiểm tra xem có loại nhân viên nào chưa, nếu chưa thì thêm vào bảng
        if (loaiNhanVienRepository.count() == 0) {
            // Tạo các loại nhân viên mặc định với role
            LoaiNhanVien owner = new LoaiNhanVien(UUID.randomUUID().toString(), "Owner", true, Role.OWNER);
            LoaiNhanVien employee = new LoaiNhanVien(UUID.randomUUID().toString(), "Employee", true, Role.EMPLOYEE);
            // Lưu vào cơ sở dữ liệu
            loaiNhanVienRepository.save(owner);
            loaiNhanVienRepository.save(employee);

            System.out.println("Đã thêm các loại nhân viên mặc định vào bảng.");
        }
    }
}
