package com.hotelmanagementsystem.hotel_service.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhongRequest {
    private String tenPhong;
    private String ghiChu;
    private String tinhTrangPhong;

    private String maLoaiPhong;     // dùng để ánh xạ đến LoaiPhong
    private String maTrangThai;     // dùng để ánh xạ đến TrangThaiPhong
}
