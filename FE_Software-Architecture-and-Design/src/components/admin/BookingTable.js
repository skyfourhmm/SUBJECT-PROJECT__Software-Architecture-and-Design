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
              Price
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
            <tr key={booking.maPhieuDat} className="hover:bg-gray-50">
              <td className="px-6 py-4">
                <div>
                  <div className="font-medium">{booking?.khachHang?.hoTen}</div>
                  <div className="text-sm text-gray-500">
                    {booking.maKhachHang}
                  </div>
                </div>
              </td>
              <td className="px-6 py-4">{booking?.phong?.tenPhong}</td>
              <td className="px-6 py-4">{booking.request}</td>
              <td className="px-6 py-4">
                {booking.checkIn} / {booking.checkOut}
              </td>
              <td className="px-6 py-4">
                <span
                  className={`px-3 py-1 rounded-full text-sm ${
                    booking.trangThai === "Đã đặt"
                      ? "bg-green-100 text-green-800"
                      : booking.trangThai === "Đã Thanh Toán"
                      ? "bg-blue-100 text-blue-800"
                      : "bg-red-100 text-red-800"
                  }`}
                >
                  {booking.trangThai}
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
                  {booking.trangThai !== "Đã đặt" && (
                    <button
                      onClick={() => onEdit(booking)}
                      className="p-1 hover:bg-gray-100 rounded"
                    >
                      📝
                    </button>
                  )}
                  <button
                    onClick={() => onConfirmOrCancel(booking)}
                    className="p-1 hover:bg-gray-100 rounded text-red-500"
                  >
                    {booking.trangThai === "Đã đặt" && "Thanh Toán"}
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
