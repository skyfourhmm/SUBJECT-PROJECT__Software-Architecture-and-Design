package com.hotelmanagementsystem.hotel_service.dto;

import java.util.List;

public class CreateRoomDTO {
    private String number;
    private Integer floor;
    private Integer roomTypeId;
    private String status;
    private List<Integer> amenityIds;

    // Constructor mặc định
    public CreateRoomDTO() {
    }

    // Getters và Setters
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Integer> getAmenityIds() {
        return amenityIds;
    }

    public void setAmenityIds(List<Integer> amenityIds) {
        this.amenityIds = amenityIds;
    }
}