package com.hotelmanagementsystem.hotel_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoaiPhongRequest {
    private String tenLoaiPhong;
    private Double dienTich;
    private String tienNghi;
    private String doiTuongSuDung;
    private String hinhAnh;
    private double giaPhongTheoDem;
    private double giaPhongTheoGio;
}