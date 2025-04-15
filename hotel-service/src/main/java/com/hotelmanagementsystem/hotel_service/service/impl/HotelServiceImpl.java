package com.hotelmanagementsystem.hotel_service.service.impl;

import com.hotelmanagementsystem.hotel_service.dto.*;
import com.hotelmanagementsystem.hotel_service.entity.*;
import com.hotelmanagementsystem.hotel_service.repository.AmenityRepository;
import com.hotelmanagementsystem.hotel_service.repository.RoomRepository;
import com.hotelmanagementsystem.hotel_service.repository.RoomTypeRepository;
import com.hotelmanagementsystem.hotel_service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private AmenityRepository amenityRepository;

    // Room operations
    @Override
    public List<RoomDTO> getHotelRooms() {
        return roomRepository.findAll().stream()
                .map(this::mapToRoomDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO addRoom(CreateRoomDTO dto) {
        if (roomRepository.existsByNumber(dto.getNumber())) {
            throw new IllegalArgumentException("Room number already exists");
        }

        RoomType roomType = roomTypeRepository.findById(dto.getRoomTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Room type not found"));

        Room room = new Room();
        room.setNumber(dto.getNumber());
        room.setFloor(dto.getFloor());
        room.setRoomType(roomType);
        room.setStatus(RoomStatus.valueOf(dto.getStatus()));

        if (dto.getAmenityIds() != null && !dto.getAmenityIds().isEmpty()) {
            List<Amenity> amenities = amenityRepository.findAllById(dto.getAmenityIds());
            if (amenities.isEmpty()) {
                throw new IllegalArgumentException("No valid amenities found");
            }
            room.setAmenities(amenities);
        }

        Room savedRoom = roomRepository.save(room);
        return mapToRoomDTO(savedRoom);
    }

    @Override
    public RoomDTO updateRoom(Integer roomId, UpdateRoomDTO dto) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        if (dto.getNumber() != null && !dto.getNumber().equals(room.getNumber())) {
            if (roomRepository.existsByNumber(dto.getNumber())) {
                throw new IllegalArgumentException("Room number already exists");
            }
            room.setNumber(dto.getNumber());
        }

        if (dto.getFloor() != null) {
            room.setFloor(dto.getFloor());
        }

        if (dto.getRoomTypeId() != null) {
            RoomType roomType = roomTypeRepository.findById(dto.getRoomTypeId())
                    .orElseThrow(() -> new IllegalArgumentException("Room type not found"));
            room.setRoomType(roomType);
        }

        if (dto.getStatus() != null) {
            room.setStatus(RoomStatus.valueOf(dto.getStatus()));
        }

        if (dto.getAmenityIds() != null) {
            List<Amenity> amenities = amenityRepository.findAllById(dto.getAmenityIds());
            room.setAmenities(amenities);
        }

        Room updatedRoom = roomRepository.save(room);
        return mapToRoomDTO(updatedRoom);
    }

    @Override
    public void deleteRoom(Integer roomId) {
        if (!roomRepository.existsById(roomId)) {
            throw new IllegalArgumentException("Room not found");
        }
        roomRepository.deleteById(roomId);
    }

    @Override
    public void assignAmenitiesToRoom(Integer roomId, List<Integer> amenityIds) {
        if (amenityIds == null || amenityIds.isEmpty()) {
            throw new IllegalArgumentException("Amenity IDs must not be empty");
        }

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        List<Amenity> amenities = amenityRepository.findAllById(amenityIds);
        if (amenities.isEmpty()) {
            throw new IllegalArgumentException("No valid amenities found");
        }

        room.setAmenities(amenities);
        roomRepository.save(room);
    }

    @Override
    public List<String> getRoomAmenities(Integer roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        return room.getAmenities().stream()
                .map(Amenity::getName)
                .collect(Collectors.toList());
    }

    // RoomType operations
    @Override
    public List<RoomTypeDTO> getRoomTypes() {
        return roomTypeRepository.findAll().stream()
                .map(this::mapToRoomTypeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomTypeDTO addRoomType(CreateRoomTypeDTO dto) {
        if (roomTypeRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Room type name already exists");
        }

        RoomType roomType = new RoomType();
        roomType.setName(dto.getName());
        roomType.setDescription(dto.getDescription());
        roomType.setPricePerNight(dto.getPricePerNight());

        RoomType savedRoomType = roomTypeRepository.save(roomType);
        return mapToRoomTypeDTO(savedRoomType);
    }

    @Override
    public RoomTypeDTO updateRoomType(Integer roomTypeId, UpdateRoomTypeDTO dto) {
        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Room type not found"));

        if (dto.getName() != null && !dto.getName().equals(roomType.getName())) {
            if (roomTypeRepository.existsByName(dto.getName())) {
                throw new IllegalArgumentException("Room type name already exists");
            }
            roomType.setName(dto.getName());
        }

        if (dto.getDescription() != null) {
            roomType.setDescription(dto.getDescription());
        }

        if (dto.getPricePerNight() != null) {
            roomType.setPricePerNight(dto.getPricePerNight());
        }

        RoomType updatedRoomType = roomTypeRepository.save(roomType);
        return mapToRoomTypeDTO(updatedRoomType);
    }

    @Override
    public void deleteRoomType(Integer roomTypeId) {
        if (!roomTypeRepository.existsById(roomTypeId)) {
            throw new IllegalArgumentException("Room type not found");
        }
        // Kiểm tra xem có phòng nào đang sử dụng loại phòng này không
        List<Room> roomsUsingType = roomRepository.findAll().stream()
                .filter(room -> room.getRoomType().getId().equals(roomTypeId))
                .collect(Collectors.toList());
        if (!roomsUsingType.isEmpty()) {
            throw new IllegalArgumentException("Cannot delete room type in use");
        }
        roomTypeRepository.deleteById(roomTypeId);
    }

    // Helper methods for mapping
    private RoomDTO mapToRoomDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setNumber(room.getNumber());
        dto.setFloor(room.getFloor());
        dto.setPrice(room.getRoomType().getPricePerNight());
        dto.setRoomType(room.getRoomType().getName());
        dto.setStatus(room.getStatus().name());
        dto.setAmenities(
                room.getAmenities().stream()
                        .map(Amenity::getName)
                        .collect(Collectors.toList())
        );
        return dto;
    }

    private RoomTypeDTO mapToRoomTypeDTO(RoomType roomType) {
        RoomTypeDTO dto = new RoomTypeDTO();
        dto.setId(roomType.getId());
        dto.setName(roomType.getName());
        dto.setDescription(roomType.getDescription());
        dto.setPricePerNight(roomType.getPricePerNight());
        return dto;
    }
}