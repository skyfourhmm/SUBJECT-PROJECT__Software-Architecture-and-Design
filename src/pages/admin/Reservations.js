import React, { useState } from "react";

function Reservations() {
  const bookings = [
    {
      id: "LG-B00113",
      guestName: "John Miller",
      roomType: "Deluxe 101",
      request: "Late Check-Out",
      nights: "3 nights",
      date: "July 10 - 13, 2023",
      status: "Confirmed"
    },
    {
      id: "LG-B00114",
      guestName: "Emily Davis",
      roomType: "Standard 202",
      request: "None",
      nights: "2 nights",
      date: "July 9 - 11, 2023",
      status: "Confirmed"
    },
    {
      id: "LG-B00115",
      guestName: "Liam Thompson",
      roomType: "Suite 203",
      request: "Extra Pillows",
      nights: "5 nights",
      date: "July 8 - 13, 2023",
      status: "Pending"
    }
  ];

  const [searchQuery, setSearchQuery] = useState("");
  const [statusFilter, setStatusFilter] = useState("all");
  const [dateRange, setDateRange] = useState("");

  const filteredBookings = bookings.filter((booking) => {
    const searchMatch = booking.guestName.toLowerCase().includes(searchQuery.toLowerCase()) ||
                        booking.status.toLowerCase().includes(searchQuery.toLowerCase()) ||
                        booking.roomType.toLowerCase().includes(searchQuery.toLowerCase());

    const statusMatch = statusFilter === "all" || booking.status.toLowerCase() === statusFilter;

    const dateMatch = !dateRange || booking.date.includes(dateRange);

    return searchMatch && statusMatch && dateMatch;
  });


  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-xl font-semibold">Reservation List</h1>
        <div className="flex gap-4">
          <input
              type="text"
              placeholder="Search guest, status, etc"
              className="pl-10 pr-4 py-2 border rounded-lg w-64"
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
            />
            <select 
              className="px-4 py-2 text-gray-700 border rounded-lg"
              value={statusFilter}
              onChange={(e) => setStatusFilter(e.target.value)}
            >
              <option value="all">All Status</option>
              <option value="confirmed">Confirmed</option>
              <option value="pending">Pending</option>
              <option value="cancelled">Cancelled</option>
            </select>
            <div className="relative">
              <input
                type="text"
                value={dateRange}
                onChange={(e) => setDateRange(e.target.value)}
                className="px-4 py-2 text-gray-700 border rounded-lg w-48"
              />
            </div>
          <button className="px-4 py-2 bg-blue-500 text-white rounded-lg flex items-center gap-2">
            + Add Booking
          </button>
        </div>
      </div>

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
            {filteredBookings.map((booking) => (
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
                  <span className={`px-3 py-1 rounded-full text-sm ${
                    booking.status === 'Confirmed' 
                      ? 'bg-green-100 text-green-800' 
                      : 'bg-blue-100 text-blue-800'
                  }`}>
                    {booking.status}
                  </span>
                </td>
                <td className="px-6 py-4">
                  <div className="flex gap-2">
                    <button className="p-1 hover:bg-gray-100 rounded">👁️</button>
                    <button className="p-1 hover:bg-gray-100 rounded">📝</button>
                    <button className="p-1 hover:bg-gray-100 rounded text-red-500">
                      {booking.status === 'Pending' ? 'Confirm' : 'Cancel'}
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Reservations;