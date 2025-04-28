import React, { useState } from "react";
import RoomsHeader from "../../components/admin/RoomsHeader";
import RoomList from "../../components/admin/RoomList";

function Rooms() {
  const rooms = [
    {
      id: 1,
      name: "Standard Room",
      type: "Queen Bed",
      size: "25 m²",
      guests: 2,
      description:
        "A cozy and budget-friendly option perfect for solo travelers or couples. Features a queen-sized bed, private bathroom, work desk, and all essential amenities for a comfortable stay.",
      availability: "16/30 Rooms",
      price: 120,
      status: "Occupied",
      image:
        "https://plus.unsplash.com/premium_photo-1661964071015-d97428970584?q=80&w=1920&auto=format&fit=crop",
    },
    {
      id: 2,
      name: "Deluxe Room",
      type: "King Bed",
      size: "35 m²",
      guests: 2,
      description:
        "Indulge in spacious luxury with a king-sized bed, separate seating area, and a larger desk. Enjoy entertainment on a 55-inch TV and relax in an en-suite bathroom featuring both a bathtub and a shower.",
      availability: "2/25 Rooms",
      price: 180,
      status: "Available",
      image:
        "https://plus.unsplash.com/premium_photo-1687960116497-0dc41e1808a2?q=80&w=2071&auto=format&fit=crop",
    },
    {
      id: 3,
      name: "Suite Room",
      type: "King Bed",
      size: "50 m²",
      guests: 3,
      description:
        "Experience premium comfort with separate living and sleeping areas. This suite features a king-sized bed, a fully furnished living room, and a kitchenette, making it perfect for extended stays or families seeking extra.",
      availability: "5/10 Rooms",
      price: 300,
      status: "Available",
      image:
        "https://plus.unsplash.com/premium_photo-1676823553207-758c7a66e9bb?q=80&w=2070&auto=format&fit=crop",
    },
  ];

  const [searchQuery, setSearchQuery] = useState("");
  const [sortBy, setSortBy] = useState("popular");
  const [roomType, setRoomType] = useState("all");

  const filteredRooms = rooms.filter((room) => {
    const nameMatch = room.name.toLowerCase().includes(searchQuery.toLowerCase());
    const typeMatch = roomType === "all" || room.type.toLowerCase().includes(roomType);
    return nameMatch && typeMatch;
  });

  const sortedRooms = [...filteredRooms].sort((a, b) => {
    switch (sortBy) {
      case "newest":
        return b.id - a.id;
      case "price_low":
        return a.price - b.price;
      case "price_high":
        return b.price - a.price;
      default:
        return 0;
    }
  });

  const handleAddRoom = () => {
    // Logic để thêm phòng mới (có thể mở modal hoặc chuyển hướng)
    console.log("Add new room");
  };

  const handleEdit = (room) => {
    // Logic để chỉnh sửa phòng
    console.log("Edit room:", room);
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
      <RoomList rooms={sortedRooms} onEdit={handleEdit} />
    </div>
  );
}

export default Rooms;