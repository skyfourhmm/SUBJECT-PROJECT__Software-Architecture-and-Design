import React, { useState } from "react";

function RecentActivities({ activities }) {
  const [showMenu, setShowMenu] = useState(null); // Lưu index của activity đang mở menu

  const handleMenuClick = (index) => {
    setShowMenu(showMenu === index ? null : index);
  };

  const handleMenuAction = (action, activity) => {
    console.log(`Performing action: ${action} on activity:`, activity);
    setShowMenu(null);
    if (action === "view_details") {
      console.log("Viewing details for activity:", activity.title);
    } else if (action === "mark_complete") {
      console.log("Marking activity as complete:", activity.title);
    } else if (action === "delete") {
      console.log("Deleting activity:", activity.title);
    }
  };

  return (
    <div className="bg-white p-6 rounded-lg shadow">
      <div className="flex justify-between items-center mb-6">
        <div>
          <h3 className="text-lg font-semibold">Recent Activities</h3>
          <div className="text-sm text-gray-500">1,242</div>
        </div>
        <button className="px-4 py-2 border rounded-lg">Popular ▼</button>
      </div>
      <div className="space-y-6">
        {activities.map((activity, index) => (
          <div key={index} className="flex items-center gap-4">
            <div className="w-8 h-8 flex items-center justify-center rounded-full bg-gray-100">
              {activity.icon}
            </div>
            <div className="flex-1">
              <div className="text-sm text-gray-500">{activity.time}</div>
              <div>{activity.title}</div>
            </div>
            <div className="relative">
              <button
                onClick={() => handleMenuClick(index)}
                className="text-gray-400"
              >
                ...
              </button>
              {showMenu === index && (
                <div className="absolute right-0 mt-2 w-48 bg-white border rounded-lg shadow-lg z-10">
                  <button
                    onClick={() => handleMenuAction("view_details", activity)}
                    className="block w-full text-left px-4 py-2 hover:bg-gray-100"
                  >
                    View Details
                  </button>
                  <button
                    onClick={() => handleMenuAction("mark_complete", activity)}
                    className="block w-full text-left px-4 py-2 hover:bg-gray-100"
                  >
                    Mark as Complete
                  </button>
                  <button
                    onClick={() => handleMenuAction("delete", activity)}
                    className="block w-full text-left px-4 py-2 hover:bg-gray-100"
                  >
                    Delete
                  </button>
                </div>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default RecentActivities;