import React, { useEffect, useRef, useState } from "react";
import StatisticsCard from "../../components/admin/StatisticsCard";
import BookingPieChart from "../../components/admin/BookingPieChart";
import RoomAvailability from "../../components/admin/RoomAvailability";
import TasksList from "../../components/admin/TasksList";
import BookingList from "../../components/admin/BookingList";
import RecentActivities from "../../components/admin/RecentActivities";
import { logout } from "../../api/user";

function Dashboard() {
  const [showShareModal, setShowShareModal] = useState(false);
  const [showWidgetModal, setShowWidgetModal] = useState(false);
  const [tasks, setTasks] = useState([
    {
      date: "June 19, 2023",
      title: "Prepare Conference Room B (10 AM)",
      status: "Completed",
    },
    {
      date: "June 19, 2023",
      title: "Restock 3rd Floor Supplies (Housekeeping)",
      status: "In Progress",
    },
    {
      date: "June 20, 2023",
      title: "Inspect and Clean Pool Area (11 AM)",
      status: "Pending",
    },
    {
      date: "June 20, 2023",
      title: "Check-In Assistance During Peak Hours (4 PM - 6 PM)",
      status: "Pending",
    },
  ]);

  const bookingData = {
    platforms: [
      { name: "Direct Booking", value: 61 },
      { name: "Booking.com", value: 12 },
      { name: "Agoda", value: 11 },
      { name: "Airbnb", value: 9 },
      { name: "Hotels.com", value: 5 },
      { name: "Others", value: 2 },
    ],
  };

  const recentActivities = [
    {
      time: "11:50 AM",
      title: "Conference Room B Ready (10 AM)",
      icon: "🔵",
    },
    {
      time: "11:30 AM",
      title: "Room B set for 10 AM meeting, with AV and refreshments.",
      icon: "🟣",
    },
    {
      time: "11:00 AM",
      title: "Room 204 cleaned and prepped for new guests.",
      icon: "🔵",
    },
    {
      time: "10:30 AM",
      title:
        "Maintenance logged: Toilet issue in Room 109, technician assigned.",
      icon: "🟣",
    },
  ];

  const bookings = [
    {
      id: "LG-B00113",
      guestName: "John Smith",
      roomType: "Deluxe",
      roomNumber: "101",
      duration: "3 nights",
      checkIn: "Jun 19, 2023",
      checkOut: "Jun 22, 2023",
      status: "Checked-in",
    },
    {
      id: "LG-B00114",
      guestName: "Alice Johnson",
      roomType: "Standard",
      roomNumber: "202",
      duration: "2 nights",
      checkIn: "Jun 19, 2023",
      checkOut: "Jun 22, 2023",
      status: "Checked-in",
    },
    {
      id: "LG-B00115",
      guestName: "Mark Davis",
      roomType: "Suite",
      roomNumber: "303",
      duration: "5 nights",
      checkIn: "Jun 19, 2023",
      checkOut: "Jun 22, 2023",
      status: "Pending",
    },
  ];

  const handleShare = () => {
    if (navigator.share) {
      navigator
        .share({
          title: "OASIS Dashboard",
          text: "Check out the latest dashboard stats for OASIS Hotel!",
          url: window.location.href,
        })
        .catch((error) => console.error("Error sharing:", error));
    } else {
      setShowShareModal(true);
    }
  };

  const handleExport = () => {
    const csvContent = [
      [
        "Booking ID",
        "Guest Name",
        "Room",
        "Duration",
        "Check In",
        "Check Out",
        "Status",
      ],
      ...bookings.map((booking) => [
        booking.id,
        booking.guestName,
        `Room ${booking.roomNumber}`,
        booking.duration,
        booking.checkIn,
        booking.checkOut,
        booking.status,
      ]),
    ]
      .map((row) => row.join(","))
      .join("\n");

    const blob = new Blob([csvContent], { type: "text/csv" });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = "dashboard_bookings.csv";
    a.click();
    window.URL.revokeObjectURL(url);
  };

  const handleCustomWidgets = () => {
    setShowWidgetModal(true);
  };

  const handleAddTask = (newTask) => {
    setTasks([...tasks, newTask]);
    console.log("Added new task:", newTask);
  };

  // Avatar click handler

  const [isOpen, setIsOpen] = useState(false);
  const dropdownRef = useRef(null);

  // Đóng dropdown khi click ra ngoài
  useEffect(() => {
    function handleClickOutside(event) {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsOpen(false); // Ẩn dropdown khi click ngoài
      }
    }
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  const handleAvatarClick = () => {
    setIsOpen(!isOpen);
  };

  // Logout handler
  const handleLogout = async () => {
    try {
      await logout();

      window.location.href = "/login"; // Redirect to login page
    } catch (error) {
      console.error("Logout failed:", error);
    }
  };

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-8">
        <div>
          <h1 className="text-2xl font-bold">Hey Prottoy,</h1>
          <p className="text-gray-600">
            Great service leaves a lasting impression.
          </p>
        </div>
        <div className="flex gap-4">
          <button
            onClick={handleShare}
            className="px-4 py-2 text-gray-600 hover:bg-gray-100 rounded"
          >
            Share
          </button>
          <button
            onClick={handleExport}
            className="px-4 py-2 text-gray-600 hover:bg-gray-100 rounded"
          >
            Export
          </button>
          <button
            onClick={handleCustomWidgets}
            className="px-4 py-2 bg-blue-500 text-white rounded"
          >
            Custom Widgets
          </button>
          <div className="flex items-center space-x-4">
            <img
              src="https://i.pravatar.cc/40" // URL hình avatar mẫu, bạn có thể thay bằng hình thật
              alt="Avatar"
              className="w-10 h-10 rounded-full object-cover cursor-pointer"
              onClick={handleAvatarClick}
            />
          </div>
        </div>
      </div>
      {isOpen && (
        <div className="absolute right-0 mt-2 w-48 bg-white border rounded shadow-lg z-50">
          <ul>
            <li className="px-4 py-2 hover:bg-gray-100 cursor-pointer">
              Profile
            </li>
            <li className="px-4 py-2 hover:bg-gray-100 cursor-pointer">
              Settings
            </li>
            <li
              className="px-4 py-2 hover:bg-gray-100 cursor-pointer"
              onClick={handleLogout}
            >
              Logout
            </li>
          </ul>
        </div>
      )}

      {showShareModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white p-6 rounded-lg shadow-lg">
            <h2 className="text-lg font-semibold mb-4">Share Dashboard</h2>
            <p className="mb-4">Copy the link or send via email:</p>
            <input
              type="text"
              value={window.location.href}
              readOnly
              className="w-full p-2 border rounded mb-4"
            />
            <div className="flex gap-4">
              <button
                onClick={() =>
                  navigator.clipboard.writeText(window.location.href)
                }
                className="px-4 py-2 bg-blue-500 text-white rounded"
              >
                Copy Link
              </button>
              <button
                onClick={() => setShowShareModal(false)}
                className="px-4 py-2 border rounded"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      )}

      {showWidgetModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white p-6 rounded-lg shadow-lg">
            <h2 className="text-lg font-semibold mb-4">Customize Widgets</h2>
            <p className="mb-4">Select widgets to display:</p>
            <div className="space-y-2">
              <label className="flex items-center gap-2">
                <input type="checkbox" defaultChecked />
                Booking Pie Chart
              </label>
              <label className="flex items-center gap-2">
                <input type="checkbox" defaultChecked />
                Room Availability
              </label>
              <label className="flex items-center gap-2">
                <input type="checkbox" defaultChecked />
                Tasks List
              </label>
            </div>
            <div className="flex gap-4 mt-4">
              <button
                onClick={() => console.log("Save widget settings")}
                className="px-4 py-2 bg-blue-500 text-white rounded"
              >
                Save
              </button>
              <button
                onClick={() => setShowWidgetModal(false)}
                className="px-4 py-2 border rounded"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      )}

      <div className="grid grid-cols-5 gap-4 mb-8">
        <StatisticsCard
          title="New Bookings"
          value="840"
          change="+4.75% from last week"
          isPrimary={true}
        />
        <StatisticsCard
          title="Check-In"
          value="231"
          change="+1 from last week"
          isPrimary={false}
        />
        <StatisticsCard
          title="Check-Out"
          value="124"
          change="-1.00% from last week"
          isPrimary={false}
        />
        <StatisticsCard
          title="Room Available"
          value="32"
          change="+2.97 from last week"
          isPrimary={false}
        />
        <StatisticsCard
          title="Total Revenue"
          value="$123,980"
          change="+5.70% from last week"
          isPrimary={false}
        />
      </div>

      <div className="grid grid-cols-3 gap-4 mb-8">
        <BookingPieChart platforms={bookingData.platforms} />
        <RoomAvailability
          occupied={286}
          reserved={87}
          available={32}
          notReady={13}
        />
        <TasksList tasks={tasks} onAddTask={handleAddTask} />
      </div>

      <div className="grid grid-cols-2 gap-4">
        <BookingList bookings={bookings} />
        <RecentActivities activities={recentActivities} />
      </div>
    </div>
  );
}

export default Dashboard;
