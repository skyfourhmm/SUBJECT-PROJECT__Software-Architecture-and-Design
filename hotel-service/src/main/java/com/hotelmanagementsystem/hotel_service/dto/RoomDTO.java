package com.hotelmanagementsystem.hotel_service.dto;

import com.hotelmanagementsystem.hotel_service.entity.Hotel;

import java.math.BigDecimal;

public class RoomDTO {
    private String number;
    private Integer floor;
    private String roomType;
    private BigDecimal price;
    private String status;

    public RoomDTO() {
        this.number = number;
        this.floor = floor;
        this.roomType = roomType;
        this.price = price;
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }
}
