// File: src/pages/admin/Rooms.jsx
import React, { useEffect, useState } from "react";
import RoomsHeader from "../../components/admin/RoomsHeader";
import RoomList from "../../components/admin/RoomList";
import RoomTypeModal from "../../components/admin/RoomTypeModal";
import { getListTypeRoom, getRoomsByTypeId } from "../../api/rooms";

function Rooms() {
  const [rooms, setRooms] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [sortBy, setSortBy] = useState("popular");
  const [roomType, setRoomType] = useState("all");

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedRoomType, setSelectedRoomType] = useState(null);
  const [roomListByType, setRoomListByType] = useState([]);

  useEffect(() => {
    const fetchTypesRooms = async () => {
      try {
        const res = await getListTypeRoom();
        if (res) {
          setRooms(res);
        }
      } catch (error) {
        console.error("Lỗi lấy thông tin loại phòng:", error);
      }
    };
    fetchTypesRooms();
  }, []);

  const handleAddRoom = () => {
    console.log("Add new room");
  };

  const handleEdit = async (roomType) => {
    try {
      const res = await getRoomsByTypeId(roomType.maLoaiPhong);
      setSelectedRoomType(roomType);
      setRoomListByType(res || []);
      setIsModalOpen(true);
    } catch (error) {
      console.error("Lỗi khi lấy danh sách phòng:", error);
    }
  };

  return (
    <div className="p-6">
      <RoomsHeader
        searchQuery={searchQuery}
        setSearchQuery={setSearchQuery}
        sortBy={sortBy}
        setSortBy={setSortBy}
        roomType={roomType}
        setRoomType={setRoomType}
        onAddRoom={handleAddRoom}
      />
      <RoomList rooms={rooms} onEdit={handleEdit} />

      <RoomTypeModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        roomType={selectedRoomType}
        roomList={roomListByType}
      />
    </div>
  );
}

export default Rooms;
