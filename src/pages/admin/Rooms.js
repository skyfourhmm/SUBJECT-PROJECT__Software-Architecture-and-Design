import React, { useState } from "react";

function Rooms() {
  const rooms = [
    {
      id: 1,
      name: "Standard Room",
      type: "Queen Bed",
      size: "25 m²",
      guests: 2,
      description: "A cozy and budget-friendly option perfect for solo travelers or couples. Features a queen-sized bed, private bathroom, work desk, and all essential amenities for a comfortable stay.",
      availability: "16/30 Rooms",
      price: 120,
      status: "Occupied",
      image: "https://plus.unsplash.com/premium_photo-1661964071015-d97428970584?q=80&w=1920&auto=format&fit=crop"
    },
    {
      id: 2,
      name: "Deluxe Room",
      type: "King Bed",
      size: "35 m²",
      guests: 2,
      description: "Indulge in spacious luxury with a king-sized bed, separate seating area, and a larger desk. Enjoy entertainment on a 55-inch TV and relax in an en-suite bathroom featuring both a bathtub and a shower.",
      availability: "2/25 Rooms",
      price: 180,
      status: "Available",
      image: "https://plus.unsplash.com/premium_photo-1687960116497-0dc41e1808a2?q=80&w=2071&auto=format&fit=crop"
    },
    {
      id: 3,
      name: "Suite Room",
      type: "King Bed",
      size: "50 m²",
      guests: 3,
      description: "Experience premium comfort with separate living and sleeping areas. This suite features a king-sized bed, a fully furnished living room, and a kitchenette, making it perfect for extended stays or families seeking extra.",
      availability: "5/10 Rooms",
      price: 300,
      status: "Available",
      image: "https://plus.unsplash.com/premium_photo-1676823553207-758c7a66e9bb?q=80&w=2070&auto=format&fit=crop"
    }
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


  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-xl font-semibold">Rooms</h1>
        <div className="flex gap-4">
          <input
            type="text"
            placeholder="Search Room Type/etc"
            className="pl-10 pr-4 py-2 border rounded-lg w-64"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
          />
          <select 
            className="px-4 py-2 border rounded-lg text-gray-700"
            value={sortBy}
            onChange={(e) => setSortBy(e.target.value)}
          >
            <option value="popular">Popular</option>
            <option value="newest">Newest</option>
            <option value="price_low">Price: Low to High</option>
            <option value="price_high">Price: High to Low</option>
          </select>
          <select 
            className="px-4 py-2 border rounded-lg text-gray-700"
            value={roomType}
            onChange={(e) => setRoomType(e.target.value)}
          >
            <option value="all">All Type</option>
            <option value="standard">Standard</option>
            <option value="deluxe">Deluxe</option>
            <option value="suite">Suite</option>
          </select>
          <button className="px-4 py-2 bg-blue-500 text-white rounded-lg flex items-center gap-2">
            + Add Room
          </button>
        </div>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        {sortedRooms.map((room) => (
          <div key={room.id} className="bg-white p-4 rounded-lg shadow flex gap-4">
            <img
              src={room.image}
              alt={room.name}
              className="w-48 h-48 object-cover rounded-lg"
            />
            <div className="flex-1">
              <div className="flex justify-between items-start mb-2">
                <div>
                  <h3 className="text-lg font-semibold">{room.name}</h3>
                  <div className="flex gap-4 text-sm text-gray-500 mt-1">
                    <span>📏 {room.size}</span>
                    <span>🛏️ {room.type}</span>
                    <span>👥 {room.guests} guests</span>
                  </div>
                </div>
                <span className={`px-3 py-1 rounded-full text-sm ${
                  room.status === 'Available' 
                    ? 'bg-green-100 text-green-800' 
                    : 'bg-red-100 text-red-800'
                }`}>
                  {room.status}
                </span>
              </div>
              <p className="text-sm text-gray-600 mb-4">{room.description}</p>
              <div className="flex justify-between items-center">
                <div className="text-sm text-gray-500">
                  Availability: {room.availability}
                </div>
                <div className="flex items-center gap-4">
                  <span className="text-lg font-semibold">${room.price}</span>
                  <span className="text-sm text-gray-500">/night</span>
                  <button className="px-4 py-1 bg-blue-500 text-white rounded">
                    Edit
                  </button>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Rooms;