package com.hotelmanagementsystem.indentity_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LoaiNhanVien")
public class LoaiNhanVien {

    @Id
    @Column(name = "maLoaiNhanVien", updatable = false, nullable = false)
    private String maLoaiNhanVien = UUID.randomUUID().toString();

    private String tenLoaiNhanVien;

    // Quan hệ 1-nhiều với NhanVien
    @OneToMany(mappedBy = "loaiNhanVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NhanVien> danhSachNhanVien;
}
