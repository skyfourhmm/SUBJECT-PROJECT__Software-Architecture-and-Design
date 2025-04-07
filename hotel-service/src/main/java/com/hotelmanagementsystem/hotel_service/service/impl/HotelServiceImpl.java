package com.hotelmanagementsystem.hotel_service.service.impl;

import com.hotelmanagementsystem.hotel_service.dto.HotelDTO;
import com.hotelmanagementsystem.hotel_service.dto.RoomDTO;
import com.hotelmanagementsystem.hotel_service.entity.Hotel;
import com.hotelmanagementsystem.hotel_service.repository.HotelRepository;
import com.hotelmanagementsystem.hotel_service.entity.Amenity;
import com.hotelmanagementsystem.hotel_service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public HotelDTO getHotelInfo(int hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        HotelDTO dto = new HotelDTO();
        dto.setName(hotel.getName());
        dto.setAddress(hotel.getAddress());
        dto.setCity(hotel.getCity());
        dto.setPhone(hotel.getPhone());
        dto.setEmail(hotel.getEmail());
        dto.setAmenities(
                hotel.getAmenities().stream()
                        .map(Amenity::getName)
                        .collect(Collectors.toList())
        );
        return dto;
    }

    @Override
    public List<RoomDTO> getHotelRooms(int hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        return hotel.getRooms().stream().map(room -> {
            RoomDTO dto = new RoomDTO();
            dto.setNumber(room.getNumber());
            dto.setFloor(room.getFloor());
            dto.setRoomType(room.getRoomType().getName());
            dto.setPrice(room.getRoomType().getPricePerNight());
            dto.setStatus(room.getStatus().name());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> getHotelAmenities(int hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        return hotel.getAmenities().stream()
                .map(Amenity::getName)
                .collect(Collectors.toList());
    }
}
