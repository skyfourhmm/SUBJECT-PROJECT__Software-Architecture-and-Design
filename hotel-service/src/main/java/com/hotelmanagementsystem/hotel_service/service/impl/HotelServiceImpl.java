package com.hotelmanagementsystem.hotel_service.service.impl;

import com.hotelmanagementsystem.hotel_service.dto.CreateHotelDTO;
import com.hotelmanagementsystem.hotel_service.dto.HotelDTO;
import com.hotelmanagementsystem.hotel_service.dto.RoomDTO;
import com.hotelmanagementsystem.hotel_service.dto.UpdateHotelDTO;
import com.hotelmanagementsystem.hotel_service.entity.Hotel;
import com.hotelmanagementsystem.hotel_service.repository.AmenityRepository;
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

    @Autowired
    private AmenityRepository amenityRepository;

    @Override
    public HotelDTO createHotel(CreateHotelDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setName(dto.getName());
        hotel.setAddress(dto.getAddress());
        hotel.setCity(dto.getCity());
        hotel.setPhone(dto.getPhone());
        hotel.setEmail(dto.getEmail());

        Hotel saved = hotelRepository.save(hotel);

        return new HotelDTO(
                saved.getName(),
                saved.getAddress(),
                saved.getCity(),
                saved.getPhone(),
                saved.getEmail(),
                saved.getAmenities().stream()
                        .map(Amenity::getName)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public HotelDTO updateHotel(int id, UpdateHotelDTO dto) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        hotel.setName(dto.getName());
        hotel.setAddress(dto.getAddress());
        hotel.setCity(dto.getCity());
        hotel.setPhone(dto.getPhone());
        hotel.setEmail(dto.getEmail());

        Hotel updated = hotelRepository.save(hotel);

        return new HotelDTO(
                updated.getName(),
                updated.getAddress(),
                updated.getCity(),
                updated.getPhone(),
                updated.getEmail(),
                updated.getAmenities().stream()
                        .map(Amenity::getName)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public void deleteHotel(int id) {
        if (!hotelRepository.existsById(id)) {
            throw new RuntimeException("Hotel not found with id: " + id);
        }
        hotelRepository.deleteById(id);
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotel -> new HotelDTO(
                        hotel.getName(),
                        hotel.getAddress(),
                        hotel.getCity(),
                        hotel.getPhone(),
                        hotel.getEmail(),
                        hotel.getAmenities().stream()
                                .map(Amenity::getName)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

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

        return hotel.getRooms().stream()
                .map(room -> {
                    RoomDTO dto = new RoomDTO();
                    dto.setNumber(room.getNumber());
                    dto.setFloor(room.getFloor());
                    dto.setPrice(room.getRoomType().getPricePerNight());
                    dto.setRoomType(room.getRoomType().getName());
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

    @Override
    public void assignAmenitiesToHotel(int hotelId, List<Integer> amenityIds) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        List<Amenity> amenities = amenityRepository.findAllById(amenityIds);
        if (amenities.isEmpty()) {
            throw new RuntimeException("No valid amenities found");
        }

        hotel.setAmenities(amenities);
        hotelRepository.save(hotel);
    }

}
