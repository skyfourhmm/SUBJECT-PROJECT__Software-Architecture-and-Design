package com.hotelmanagementsystem.hotel_service.service;

import com.hotelmanagementsystem.hotel_service.entity.Room;
import com.hotelmanagementsystem.hotel_service.entity.RoomStatus;
import com.hotelmanagementsystem.hotel_service.reponseStatus.RoomNotFoundException;
import com.hotelmanagementsystem.hotel_service.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final RoomRepository roomRepository;

    // Thêm phòng mới
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    // Sửa thông tin phòng
    public Room updateRoom(UUID roomId, Room roomDetails) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id " + roomId));

        // Cập nhật thông tin phòng
        room.setRoomNumber(roomDetails.getRoomNumber());
        room.setRoomType(roomDetails.getRoomType());
        room.setStatus(roomDetails.getStatus());

        return roomRepository.save(room);
    }

    // Xóa phòng
    public void deleteRoom(UUID roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id " + roomId));

        roomRepository.delete(room);
    }

    // Lấy danh sách tất cả các phòng
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Lấy danh sách các phòng theo trạng thái
    public List<Room> getRoomsByStatus(RoomStatus status) {
        return roomRepository.findByStatus(status);
    }

    // Thay đổi trạng thái phòng
    public Room changeRoomStatus(UUID roomId, RoomStatus newStatus) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id " + roomId));

        room.setStatus(newStatus);
        return roomRepository.save(room);
    }

}
