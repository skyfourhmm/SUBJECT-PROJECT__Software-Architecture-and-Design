// components/admin/RoomsHeader.js
import React from "react";

function RoomsHeader({
  searchQuery,
  setSearchQuery,
  sortBy,
  setSortBy,
  roomType,
  setRoomType,
  onAddRoom,
}) {
  return (
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
        <button
          onClick={onAddRoom}
          className="px-4 py-2 bg-blue-500 text-white rounded-lg flex items-center gap-2"
        >
          + Add Room
        </button>
      </div>
    </div>
  );
}

export default RoomsHeader;