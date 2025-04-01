package com.hotelmanagementsystem.indentity_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LoaiNhanVien")
public class LoaiNhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maLoaiNhanVien")
    private Long maLoaiNhanVien;

    private String tenLoaiNhanVien;
    private boolean trangThai;

    // Getters & Setters
}