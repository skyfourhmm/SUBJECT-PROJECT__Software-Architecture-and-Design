package com.hotelmanagementsystem.hotel_service.dto;

import java.util.List;

public class HotelDTO {
    private String name;
    private String address;
    private String city;
    private String phone;
    private String email;
    private List<String> amenities;

    public HotelDTO() {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.amenities = amenities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
}
