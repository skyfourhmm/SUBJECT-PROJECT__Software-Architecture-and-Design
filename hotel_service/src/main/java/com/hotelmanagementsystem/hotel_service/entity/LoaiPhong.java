package com.hotelmanagementsystem.hotel_service.entity;

import java.math.BigDecimal;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "loai_phong")
public class LoaiPhong {

    @Id
    @Column(name = "ma_loai_phong", updatable = false, nullable = false)
    private String maLoaiPhong = UUID.randomUUID().toString();

    @Column(name = "ten_loai_phong")
    private String tenLoaiPhong;

    @Column(name = "dien_tich")
    private Double dienTich;

    @Column(name = "tien_nghi")
    private String tienNghi;

    @Column(name = "doi_tuong_su_dung")
    private String doiTuongSuDung;

    @Column(name = "hinh_anh")
    private String hinhAnh;

    @Column(name = "gia_phong_theo_dem")
    private double giaPhongTheoDem;

    @Column(name = "gia_phong_theo_gio")
    private double giaPhongTheoGio;

    @OneToMany(mappedBy = "loaiPhong", cascade = CascadeType.ALL)
    @JsonIgnore // Thêm dòng này
    private List<Phong> danhSachPhong;

    // constructor, getter, setter
}