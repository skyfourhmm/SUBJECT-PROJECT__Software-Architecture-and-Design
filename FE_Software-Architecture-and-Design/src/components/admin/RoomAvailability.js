import React, { useState } from "react";

function RoomAvailability({ occupied, reserved, available, notReady }) {
  const [showMenu, setShowMenu] = useState(false);

  const handleMenuClick = () => {
    setShowMenu(!showMenu);
  };

  const handleMenuAction = (action) => {
    console.log(`Performing action: ${action}`);
    setShowMenu(false);
    if (action === "refresh") {
      console.log("Refreshing Room Availability data...");
    } else if (action === "view_details") {
      console.log("Viewing detailed room status...");
    } else if (action === "remove") {
      console.log("Removing Room Availability widget...");
    }
  };

  return (
    <div className="bg-white p-6 rounded-lg shadow relative">
      <div className="flex justify-between items-center mb-6">
        <h3 className="text-lg font-semibold">Room Availability</h3>
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
      <div className="space-y-4">
        <div className="flex justify-between items-center">
          <div>
            <div className="text-4xl font-bold">{occupied}</div>
            <div className="text-gray-500">Occupied</div>
          </div>
          <div>
            <div className="text-4xl font-bold">{reserved}</div>
            <div className="text-gray-500">Reserved</div>
          </div>
          <div>
            <div className="text-4xl font-bold">{available}</div>
            <div className="text-gray-500">Available</div>
          </div>
          <div>
            <div className="text-4xl font-bold">{notReady}</div>
            <div className="text-gray-500">Not Ready</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default RoomAvailability;