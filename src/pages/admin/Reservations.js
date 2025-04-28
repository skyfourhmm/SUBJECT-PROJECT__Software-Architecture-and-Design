import React, { useState } from "react";
import ReservationHeader from "../../components/admin/ReservationHeader";
import BookingTable from "../../components/admin/BookingTable";

function Reservations() {
  const [bookings, setBookings] = useState([
    {
      id: "LG-B00113",
      guestName: "John Miller",
      roomType: "Deluxe 101",
      request: "Late Check-Out",
      nights: "3 nights",
      date: "July 10 - 13, 2023",
      status: "Confirmed",
    },
    {
      id: "LG-B00114",
      guestName: "Emily Davis",
      roomType: "Standard 202",
      request: "None",
      nights: "2 nights",
      date: "July 9 - 11, 2023",
      status: "Confirmed",
    },
    {
      id: "LG-B00115",
      guestName: "Liam Thompson",
      roomType: "Suite 203",
      request: "Extra Pillows",
      nights: "5 nights",
      date: "July 8 - 13, 2023",
      status: "Pending",
    },
  ]);

  const [searchQuery, setSearchQuery] = useState("");
  const [statusFilter, setStatusFilter] = useState("all");
  const [dateRange, setDateRange] = useState("");
  const [showAddModal, setShowAddModal] = useState(false);
  const [showViewModal, setShowViewModal] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [selectedBooking, setSelectedBooking] = useState(null);
  const [newBooking, setNewBooking] = useState({
    id: "",
    guestName: "",
    roomType: "",
    request: "",
    nights: "",
    date: "",
    status: "Pending",
  });

  const filteredBookings = bookings.filter((booking) => {
    const searchMatch =
      booking.guestName.toLowerCase().includes(searchQuery.toLowerCase()) ||
      booking.status.toLowerCase().includes(searchQuery.toLowerCase()) ||
      booking.roomType.toLowerCase().includes(searchQuery.toLowerCase());

    const statusMatch =
      statusFilter === "all" || booking.status.toLowerCase() === statusFilter;

    const dateMatch = !dateRange || booking.date.includes(dateRange);

    return searchMatch && statusMatch && dateMatch;
  });

  const handleAddBooking = () => {
    setNewBooking({
      id: `LG-B${Math.floor(10000 + Math.random() * 90000)}`,
      guestName: "",
      roomType: "",
      request: "",
      nights: "",
      date: "",
      status: "Pending",
    });
    setShowAddModal(true);
  };

  const handleSaveNewBooking = () => {
    if (
      newBooking.guestName &&
      newBooking.roomType &&
      newBooking.nights &&
      newBooking.date
    ) {
      setBookings([...bookings, newBooking]);
      setShowAddModal(false);
      setNewBooking({
        id: "",
        guestName: "",
        roomType: "",
        request: "",
        nights: "",
        date: "",
        status: "Pending",
      });
    } else {
      alert("Please fill in all required fields.");
    }
  };

  const handleView = (booking) => {
    setSelectedBooking(booking);
    setShowViewModal(true);
  };

  const handleEdit = (booking) => {
    setSelectedBooking(booking);
    setNewBooking(booking);
    setShowEditModal(true);
  };

  const handleSaveEdit = () => {
    if (
      newBooking.guestName &&
      newBooking.roomType &&
      newBooking.nights &&
      newBooking.date
    ) {
      setBookings(
        bookings.map((b) => (b.id === newBooking.id ? newBooking : b))
      );
      setShowEditModal(false);
      setNewBooking({
        id: "",
        guestName: "",
        roomType: "",
        request: "",
        nights: "",
        date: "",
        status: "Pending",
      });
    } else {
      alert("Please fill in all required fields.");
    }
  };

  const handleConfirmOrCancel = (booking) => {
    const action = booking.status === "Pending" ? "confirm" : "cancel";
    if (window.confirm(`Are you sure you want to ${action} this booking?`)) {
      setBookings(
        bookings.map((b) =>
          b.id === booking.id
            ? {
                ...b,
                status: action === "confirm" ? "Confirmed" : "Cancelled",
              }
            : b
        )
      );
    }
  };

  return (
    <div className="p-6">
      <ReservationHeader
        searchQuery={searchQuery}
        setSearchQuery={setSearchQuery}
        statusFilter={statusFilter}
        setStatusFilter={setStatusFilter}
        dateRange={dateRange}
        setDateRange={setDateRange}
        onAddBooking={handleAddBooking}
      />
      <BookingTable
        bookings={filteredBookings}
        onView={handleView}
        onEdit={handleEdit}
        onConfirmOrCancel={handleConfirmOrCancel}
      />

      {/* Modal for Add Booking */}
      {showAddModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white p-6 rounded-lg shadow-lg w-96">
            <h2 className="text-lg font-semibold mb-4">Add New Booking</h2>
            <div className="space-y-4">
              <input
                type="text"
                placeholder="Guest Name"
                value={newBooking.guestName}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, guestName: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <input
                type="text"
                placeholder="Room Type"
                value={newBooking.roomType}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, roomType: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <input
                type="text"
                placeholder="Request (optional)"
                value={newBooking.request}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, request: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <input
                type="text"
                placeholder="Nights (e.g., 3 nights)"
                value={newBooking.nights}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, nights: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <input
                type="text"
                placeholder="Date (e.g., July 10 - 13, 2023)"
                value={newBooking.date}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, date: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <select
                value={newBooking.status}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, status: e.target.value })
                }
                className="w-full p-2 border rounded"
              >
                <option value="Pending">Pending</option>
                <option value="Confirmed">Confirmed</option>
                <option value="Cancelled">Cancelled</option>
              </select>
            </div>
            <div className="flex gap-4 mt-4">
              <button
                onClick={handleSaveNewBooking}
                className="px-4 py-2 bg-blue-500 text-white rounded"
              >
                Save
              </button>
              <button
                onClick={() => setShowAddModal(false)}
                className="px-4 py-2 border rounded"
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Modal for View Booking */}
      {showViewModal && selectedBooking && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white p-6 rounded-lg shadow-lg w-96">
            <h2 className="text-lg font-semibold mb-4">Booking Details</h2>
            <div className="space-y-2">
              <p><strong>ID:</strong> {selectedBooking.id}</p>
              <p><strong>Guest Name:</strong> {selectedBooking.guestName}</p>
              <p><strong>Room Type:</strong> {selectedBooking.roomType}</p>
              <p><strong>Request:</strong> {selectedBooking.request || "None"}</p>
              <p><strong>Duration:</strong> {selectedBooking.nights}</p>
              <p><strong>Check-In/Out:</strong> {selectedBooking.date}</p>
              <p><strong>Status:</strong> {selectedBooking.status}</p>
            </div>
            <div className="flex justify-end mt-4">
              <button
                onClick={() => setShowViewModal(false)}
                className="px-4 py-2 border rounded"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Modal for Edit Booking */}
      {showEditModal && selectedBooking && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white p-6 rounded-lg shadow-lg w-96">
            <h2 className="text-lg font-semibold mb-4">Edit Booking</h2>
            <div className="space-y-4">
              <input
                type="text"
                placeholder="Guest Name"
                value={newBooking.guestName}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, guestName: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <input
                type="text"
                placeholder="Room Type"
                value={newBooking.roomType}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, roomType: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <input
                type="text"
                placeholder="Request (optional)"
                value={newBooking.request}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, request: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <input
                type="text"
                placeholder="Nights (e.g., 3 nights)"
                value={newBooking.nights}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, nights: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <input
                type="text"
                placeholder="Date (e.g., July 10 - 13, 2023)"
                value={newBooking.date}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, date: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <select
                value={newBooking.status}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, status: e.target.value })
                }
                className="w-full p-2 border rounded"
              >
                <option value="Pending">Pending</option>
                <option value="Confirmed">Confirmed</option>
                <option value="Cancelled">Cancelled</option>
              </select>
            </div>
            <div className="flex gap-4 mt-4">
              <button
                onClick={handleSaveEdit}
                className="px-4 py-2 bg-blue-500 text-white rounded"
              >
                Save
              </button>
              <button
                onClick={() => setShowEditModal(false)}
                className="px-4 py-2 border rounded"
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default Reservations;