package com.hotelmanagementsystem.hotel_service.service;

import com.hotelmanagementsystem.hotel_service.dto.HotelDTO;
import com.hotelmanagementsystem.hotel_service.dto.RoomDTO;

import java.util.List;

public interface HotelService {
    HotelDTO getHotelInfo(int hotelId);
    List<RoomDTO> getHotelRooms(int hotelId);
    List<String> getHotelAmenities(int hotelId);
}
