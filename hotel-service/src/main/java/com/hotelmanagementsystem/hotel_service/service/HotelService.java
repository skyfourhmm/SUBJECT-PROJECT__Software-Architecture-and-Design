package com.hotelmanagementsystem.hotel_service.service;

import com.hotelmanagementsystem.hotel_service.dto.CreateHotelDTO;
import com.hotelmanagementsystem.hotel_service.dto.HotelDTO;
import com.hotelmanagementsystem.hotel_service.dto.RoomDTO;
import com.hotelmanagementsystem.hotel_service.dto.UpdateHotelDTO;

import java.util.List;

public interface HotelService {
    HotelDTO createHotel(CreateHotelDTO dto);
    HotelDTO updateHotel(int hotelId, UpdateHotelDTO dto);
    void deleteHotel(int hotelId);
    List<HotelDTO> getAllHotels();
    HotelDTO getHotelInfo(int hotelId);
    List<RoomDTO> getHotelRooms(int hotelId);
    List<String> getHotelAmenities(int hotelId);
    void assignAmenitiesToHotel(int hotelId, List<Integer> amenityIds);
}
