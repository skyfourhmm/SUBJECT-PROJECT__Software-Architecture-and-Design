// components/admin/RoomList.js
import React from "react";

function RoomList({ rooms, onEdit }) {
  return (
    <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
      {rooms.map((room) => (
        <div
          key={room.maLoaiPhong}
          className="bg-white p-4 rounded-lg shadow flex gap-4"
        >
          <img
            src={room.hinhAnh}
            alt={room.tenLoaiPhong}
            className="w-48 h-48 object-cover rounded-lg"
          />
          <div className="flex-1">
            <div className="flex justify-between items-start mb-2">
              <div>
                <h3 className="text-lg font-semibold">{room.tenLoaiPhong}</h3>
                <div className="flex gap-4 text-sm text-gray-500 mt-1">
                  <span>📏 {room.dienTich}</span>
                  <span>🛏️ {room.type}</span>
                  <span>👥 {room.guests} guests</span>
                </div>
              </div>
              <span
                className={`px-3 py-1 rounded-full text-sm ${
                  room.status === "Available"
                    ? "bg-green-100 text-green-800"
                    : "bg-red-100 text-red-800"
                }`}
              >
                {room.status}
              </span>
            </div>
            <p className="text-sm text-gray-600 mb-4">{room.tienNghi}</p>
            <div className="flex justify-between items-center">
              <div className="text-sm text-gray-500">
                Availability: {room.availability}
              </div>
              <div className="flex items-center gap-4">
                <span className="text-lg font-semibold">
                  ${room.giaPhongTheoDem}
                </span>
                <span className="text-sm text-gray-500">/night</span>
                <button
                  onClick={() => onEdit(room)}
                  className="px-4 py-1 bg-blue-500 text-white rounded"
                >
                  Edit
                </button>
              </div>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}

export default RoomList;
