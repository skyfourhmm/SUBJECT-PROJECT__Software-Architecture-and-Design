import React, { useState } from "react";
import { PieChart, Pie, Cell } from "recharts";

const COLORS = ["#4F46E5", "#3B82F6", "#60A5FA", "#93C5FD", "#BFDBFE", "#DBEAFE"];

function BookingPieChart({ platforms }) {
  const [showMenu, setShowMenu] = useState(false);

  const handleMenuClick = () => {
    setShowMenu(!showMenu);
  };

  const handleMenuAction = (action) => {
    console.log(`Performing action: ${action}`);
    setShowMenu(false);
    if (action === "refresh") {
      console.log("Refreshing Booking Pie Chart data...");
    } else if (action === "view_details") {
      console.log("Viewing detailed platform stats...");
    } else if (action === "remove") {
      console.log("Removing Booking Pie Chart widget...");
    }
  };

  return (
    <div className="bg-white p-6 rounded-lg shadow relative">
      <div className="flex justify-between items-center mb-6">
        <h3 className="text-lg font-semibold">Booking by Platform</h3>
        <div className="relative">
          <button onClick={handleMenuClick} className="text-gray-400">
            ...
          </button>
          {showMenu && (
            <div className="absolute right-0 mt-2 w-48 bg-white border rounded-lg shadow-lg z-10">
              <button
                onClick={() => handleMenuAction("refresh")}
                className="block w-full text-left px-4 py-2 hover:bg-gray-100"
              >
                Refresh
              </button>
              <button
                onClick={() => handleMenuAction("view_details")}
                className="block w-full text-left px-4 py-2 hover:bg-gray-100"
              >
                View Details
              </button>
              <button
                onClick={() => handleMenuAction("remove")}
                className="block w-full text-left px-4 py-2 hover:bg-gray-100"
              >
                Remove
              </button>
            </div>
          )}
        </div>
      </div>
      <PieChart width={300} height={200}>
        <Pie
          data={platforms}
          cx={150}
          cy={100}
          innerRadius={60}
          outerRadius={80}
          paddingAngle={5}
          dataKey="value"
        >
          {platforms.map((entry, index) => (
            <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
          ))}
        </Pie>
      </PieChart>
      <div className="mt-4 space-y-2">
        {platforms.map((platform, index) => (
          <div key={index} className="flex items-center justify-between">
            <div className="flex items-center">
              <div
                className="w-3 h-3 rounded-full mr-2"
                style={{ backgroundColor: COLORS[index] }}
              ></div>
              <span>{platform.name}</span>
            </div>
            <span>{platform.value}%</span>
          </div>
        ))}
      </div>
    </div>
  );
}

export default BookingPieChart;