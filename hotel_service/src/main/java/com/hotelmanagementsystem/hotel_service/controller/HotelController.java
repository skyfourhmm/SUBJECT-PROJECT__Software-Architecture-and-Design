package com.hotelmanagementsystem.hotel_service.controller;

import com.hotelmanagementsystem.hotel_service.entity.Room;
import com.hotelmanagementsystem.hotel_service.entity.RoomStatus;
import com.hotelmanagementsystem.hotel_service.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    // Thêm phòng mới
    @PostMapping("/rooms")
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        Room newRoom = hotelService.addRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRoom);
    }

    // Sửa thông tin phòng
    @PutMapping("/rooms/{roomId}")
    public ResponseEntity<Room> updateRoom(@PathVariable UUID roomId, @RequestBody Room roomDetails) {
        Room updatedRoom = hotelService.updateRoom(roomId, roomDetails);
        return ResponseEntity.ok(updatedRoom);
    }

    // Xóa phòng
    @DeleteMapping("/rooms/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable UUID roomId) {
        hotelService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    // Lấy danh sách tất cả phòng
    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = hotelService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    // Lấy danh sách phòng theo trạng thái
    @GetMapping("/rooms/status")
    public ResponseEntity<List<Room>> getRoomsByStatus(@RequestParam RoomStatus status) {
        List<Room> rooms = hotelService.getRoomsByStatus(status);
        return ResponseEntity.ok(rooms);
    }

    // Thay đổi trạng thái phòng
    @PutMapping("/rooms/{roomId}/status")
    public ResponseEntity<Room> changeRoomStatus(@PathVariable UUID roomId, @RequestParam RoomStatus status) {
        Room updatedRoom = hotelService.changeRoomStatus(roomId, status);
        return ResponseEntity.ok(updatedRoom);
    }
}

