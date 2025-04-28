import React from "react";

function BookingTable({ bookings, onView, onEdit, onConfirmOrCancel }) {
  return (
    <div className="bg-white rounded-lg shadow">
      <table className="w-full">
        <thead className="bg-gray-50">
          <tr>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Guest
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Room
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Request
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Duration
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Check-In/Out
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Status
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Action
            </th>
          </tr>
        </thead>
        <tbody className="divide-y divide-gray-200">
          {bookings.map((booking) => (
            <tr key={booking.id} className="hover:bg-gray-50">
              <td className="px-6 py-4">
                <div>
                  <div className="font-medium">{booking.guestName}</div>
                  <div className="text-sm text-gray-500">{booking.id}</div>
                </div>
              </td>
              <td className="px-6 py-4">{booking.roomType}</td>
              <td className="px-6 py-4">{booking.request}</td>
              <td className="px-6 py-4">{booking.nights}</td>
              <td className="px-6 py-4">{booking.date}</td>
              <td className="px-6 py-4">
                <span
                  className={`px-3 py-1 rounded-full text-sm ${
                    booking.status === "Confirmed"
                      ? "bg-green-100 text-green-800"
                      : booking.status === "Pending"
                      ? "bg-blue-100 text-blue-800"
                      : "bg-red-100 text-red-800"
                  }`}
                >
                  {booking.status}
                </span>
              </td>
              <td className="px-6 py-4">
                <div className="flex gap-2">
                  <button
                    onClick={() => onView(booking)}
                    className="p-1 hover:bg-gray-100 rounded"
                  >
                    👁️
                  </button>
                  <button
                    onClick={() => onEdit(booking)}
                    className="p-1 hover:bg-gray-100 rounded"
                  >
                    📝
                  </button>
                  <button
                    onClick={() => onConfirmOrCancel(booking)}
                    className="p-1 hover:bg-gray-100 rounded text-red-500"
                  >
                    {booking.status === "Pending" ? "Confirm" : "Cancel"}
                  </button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default BookingTable;