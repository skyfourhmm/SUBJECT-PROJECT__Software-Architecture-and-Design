package com.hotelmanagementsystem.hotel_service.entity;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trang_thai_phong")
public class TrangThaiPhong {

    @Id
    @Column(name = "ma_trang_thai", updatable = false, nullable = false)
    private String maTrangThai =UUID.randomUUID().toString();;

    @Column(name = "ten_trang_thai")
    private String tenTrangThai;

    @OneToMany(mappedBy = "trangThaiPhong", cascade = CascadeType.ALL)
    @JsonIgnore // Thêm dòng này
    private List<Phong> danhSachPhong;


    // constructor, getter, setter
}