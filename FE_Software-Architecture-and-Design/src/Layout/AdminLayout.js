import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import Dashboard from "../pages/admin/Dashboard";
import Reservations from "../pages/admin/Reservations";
import Rooms from "../pages/admin/Rooms";
import Message from "../pages/admin/Message";
import Restaurant from "../pages/admin/Restaurant";
import CustomerList from "../components/admin/CustomerList";
import Bills from "../pages/admin/Bills";

function AdminLayout() {
  return (
    <div className="flex h-screen bg-gray-100">
      {/* Sidebar */}
      <div className="w-64 bg-white shadow-lg">
        <div className="p-4 border-b flex items-center gap-3">
          <img src="/logo.png" alt="OASIS Logo" className="h-10 w-10" />
          <div>
            <h2 className="text-2xl font-bold">OASIS</h2>
            <p className="text-sm text-gray-600">HOTEL & BAR</p>
          </div>
        </div>
        <nav className="p-4">
          <ul className="space-y-2">
            <li>
              <Link
                to="/admin"
                className="flex items-center p-2 text-gray-600 hover:bg-blue-50 rounded"
              >
                <span className="mr-3">🏠</span>
                Home
              </Link>
            </li>
            <li>
              <Link
                to="/admin/guests"
                className="flex items-center p-2 text-gray-600 hover:bg-blue-50 rounded"
              >
                <span className="mr-3">👥</span>
                Guests
              </Link>
            </li>
            <li>
              <Link
                to="/admin/reservations"
                className="flex items-center p-2 text-gray-600 hover:bg-blue-50 rounded"
              >
                <span className="mr-3">📅</span>
                Reservations
              </Link>
            </li>
            <li>
              <Link
                to="/admin/rooms"
                className="flex items-center p-2 text-gray-600 hover:bg-blue-50 rounded"
              >
                <span className="mr-3">🛏️</span>
                Rooms
              </Link>
            </li>
            <li>
              <Link
                to="/admin/restaurant"
                className="flex items-center p-2 text-gray-600 hover:bg-blue-50 rounded"
              >
                <span className="mr-3">🍽️</span>
                Restaurant
              </Link>
            </li>
            <li>
              <Link
                to="/admin/bills"
                className="flex items-center p-2 text-gray-600 hover:bg-blue-50 rounded"
              >
                <span className="mr-3">🅿️</span>
                Bills
              </Link>
            </li>
            <li>
              <Link
                to="/admin/messages"
                className="flex items-center p-2 text-gray-600 hover:bg-blue-50 rounded"
              >
                <span className="mr-3">💬</span>
                Messages
              </Link>
            </li>
            <li>
              <Link
                to="/admin/settings"
                className="flex items-center p-2 text-gray-600 hover:bg-blue-50 rounded"
              >
                <span className="mr-3">⚙️</span>
                Settings
              </Link>
            </li>
          </ul>
        </nav>
      </div>

      {/* Main Content */}
      <div className="flex-1 overflow-auto">
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/reservations" element={<Reservations />} />
          <Route path="/guests" element={<CustomerList />} />
          <Route path="/rooms" element={<Rooms />} />
          <Route path="/restaurant" element={<Restaurant />} />
          <Route path="/bills" element={<Bills />} />
          <Route path="/messages" element={<Message />} />
        </Routes>
      </div>
    </div>
  );
}

export default AdminLayout;
