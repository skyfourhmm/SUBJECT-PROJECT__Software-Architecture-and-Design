// components/admin/BookingList.js
import React from "react";

function BookingList({ bookings }) {
  return (
    <div className="bg-white p-6 rounded-lg shadow">
      <div className="flex justify-between items-center mb-6">
        <h3 className="text-lg font-semibold">Booking List</h3>
        <div className="flex gap-4">
          <input
            type="text"
            placeholder="Search guest, status, etc"
            className="px-4 py-2 border rounded-lg"
          />
          <button className="px-4 py-2 border rounded-lg">All Status ▼</button>
        </div>
      </div>
      <table className="w-full">
        <thead>
          <tr className="text-left text-gray-500">
            <th className="pb-4">Booking ID</th>
            <th className="pb-4">Guest Name</th>
            <th className="pb-4">Room</th>
            <th className="pb-4">Duration</th>
            <th className="pb-4">Check In/Out</th>
            <th className="pb-4">Status</th>
          </tr>
        </thead>
        <tbody>
          {bookings.map((booking) => (
            <tr key={booking.id} className="border-t">
              <td className="py-4">{booking.id}</td>
              <td className="py-4">{booking.guestName}</td>
              <td className="py-4">Room {booking.roomNumber}</td>
              <td className="py-4">{booking.duration}</td>
              <td className="py-4">{booking.checkIn}</td>
              <td className="py-4">
                <span
                  className={`px-3 py-1 rounded-full text-sm ${
                    booking.status === "Checked-in"
                      ? "bg-green-100 text-green-800"
                      : "bg-blue-100 text-blue-800"
                  }`}
                >
                  {booking.status}
                </span>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default BookingList;