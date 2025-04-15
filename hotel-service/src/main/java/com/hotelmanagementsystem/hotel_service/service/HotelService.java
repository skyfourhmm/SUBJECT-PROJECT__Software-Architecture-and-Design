package com.hotelmanagementsystem.hotel_service.service;

import com.hotelmanagementsystem.hotel_service.dto.*;

import java.util.List;

public interface HotelService {
    // Room operations
    List<RoomDTO> getHotelRooms();
    RoomDTO addRoom(CreateRoomDTO dto);
    RoomDTO updateRoom(Integer roomId, UpdateRoomDTO dto);
    void deleteRoom(Integer roomId);
    void assignAmenitiesToRoom(Integer roomId, List<Integer> amenityIds);
    List<String> getRoomAmenities(Integer roomId);

    // RoomType operations
    List<RoomTypeDTO> getRoomTypes();
    RoomTypeDTO addRoomType(CreateRoomTypeDTO dto);
    RoomTypeDTO updateRoomType(Integer roomTypeId, UpdateRoomTypeDTO dto);
    void deleteRoomType(Integer roomTypeId);
}