package com.hotelmanagementsystem.hotel_service.dto;

import java.math.BigDecimal;

public class UpdateRoomTypeDTO {
    private String name;
    private String description;
    private BigDecimal pricePerNight;

    // Constructor mặc định
    public UpdateRoomTypeDTO() {
    }

    // Getters và Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}