import React from "react";

function ReservationHeader({
  searchQuery,
  setSearchQuery,
  statusFilter,
  setStatusFilter,
  dateRange,
  setDateRange,
  onAddBooking,
}) {
  return (
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
            placeholder="Filter by date"
            className="px-4 py-2 text-gray-700 border rounded-lg w-48"
          />
        </div>
        <button
          onClick={onAddBooking}
          className="px-4 py-2 bg-blue-500 text-white rounded-lg flex items-center gap-2"
        >
          + Add Booking
        </button>
      </div>
    </div>
  );
}

export default ReservationHeader;