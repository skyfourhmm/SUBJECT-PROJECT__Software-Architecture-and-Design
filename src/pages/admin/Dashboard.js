
import React from "react";
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, PieChart, Pie, Cell } from 'recharts';

function Dashboard() {
  const bookingData = {
    platforms: [
      { name: "Direct Booking", value: 61 },
      { name: "Booking.com", value: 12 },
      { name: "Agoda", value: 11 },
      { name: "Airbnb", value: 9 },
      { name: "Hotels.com", value: 5 },
      { name: "Others", value: 2 },
    ]
  };

  const tasks = [
    {
      date: "June 19, 2023",
      title: "Prepare Conference Room B (10 AM)",
      status: "Completed"
    },
    {
      date: "June 19, 2023",
      title: "Restock 3rd Floor Supplies (Housekeeping)",
      status: "In Progress"
    },
    {
      date: "June 20, 2023",
      title: "Inspect and Clean Pool Area (11 AM)",
      status: "Pending"
    },
    {
      date: "June 20, 2023",
      title: "Check-In Assistance During Peak Hours (4 PM - 6 PM)",
      status: "Pending"
    }
  ];

  const recentActivities = [
    {
      time: "11:50 AM",
      title: "Conference Room B Ready (10 AM)",
      icon: "🔵"
    },
    {
      time: "11:30 AM",
      title: "Room B set for 10 AM meeting, with AV and refreshments.",
      icon: "🟣"
    },
    {
      time: "11:00 AM",
      title: "Room 204 cleaned and prepped for new guests.",
      icon: "🔵"
    },
    {
      time: "10:30 AM",
      title: "Maintenance logged: Toilet issue in Room 109, technician assigned.",
      icon: "🟣"
    }
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
      status: "Checked-in"
    },
    {
      id: "LG-B00114",
      guestName: "Alice Johnson",
      roomType: "Standard",
      roomNumber: "202",
      duration: "2 nights",
      checkIn: "Jun 19, 2023",
      checkOut: "Jun 22, 2023",
      status: "Checked-in"
    },
    {
      id: "LG-B00115",
      guestName: "Mark Davis",
      roomType: "Suite",
      roomNumber: "303",
      duration: "5 nights",
      checkIn: "Jun 19, 2023",
      checkOut: "Jun 22, 2023",
      status: "Pending"
    }
  ];

  const COLORS = ['#4F46E5', '#3B82F6', '#60A5FA', '#93C5FD', '#BFDBFE', '#DBEAFE'];

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-8">
        <div>
          <h1 className="text-2xl font-bold">Hey Prottoy,</h1>
          <p className="text-gray-600">Great service leaves a lasting impression.</p>
        </div>
        <div className="flex gap-4">
          <button className="px-4 py-2 text-gray-600 hover:bg-gray-100 rounded">Share</button>
          <button className="px-4 py-2 text-gray-600 hover:bg-gray-100 rounded">Export</button>
          <button className="px-4 py-2 bg-blue-500 text-white rounded">Custom Widgets</button>
        </div>
      </div>

      <div className="grid grid-cols-5 gap-4 mb-8">
        <div className="bg-blue-500 text-white p-4 rounded-lg">
          <div className="mb-4">New Bookings</div>
          <div className="text-4xl font-bold">840</div>
          <div className="text-sm mt-2 text-blue-100">+4.75% from last week</div>
        </div>
        <div className="bg-white p-4 rounded-lg shadow">
          <div className="flex items-center justify-between mb-4">
            <span>Check-In</span>
            <span className="text-green-500">+1</span>
          </div>
          <div className="text-4xl font-bold">231</div>
          <div className="text-sm text-gray-500 mt-2">from last week</div>
        </div>
        <div className="bg-white p-4 rounded-lg shadow">
          <div className="flex items-center justify-between mb-4">
            <span>Check-Out</span>
            <span className="text-red-500">-1.00%</span>
          </div>
          <div className="text-4xl font-bold">124</div>
          <div className="text-sm text-gray-500 mt-2">from last week</div>
        </div>
        <div className="bg-white p-4 rounded-lg shadow">
          <div className="flex items-center justify-between mb-4">
            <span>Room Available</span>
            <span className="text-green-500">+2.97</span>
          </div>
          <div className="text-4xl font-bold">32</div>
          <div className="text-sm text-gray-500 mt-2">from last week</div>
        </div>
        <div className="bg-white p-4 rounded-lg shadow">
          <div className="flex items-center justify-between mb-4">
            <span>Total Revenue</span>
            <span className="text-green-500">+5.70%</span>
          </div>
          <div className="text-4xl font-bold">$123,980</div>
          <div className="text-sm text-gray-500 mt-2">from last week</div>
        </div>
      </div>

      <div className="grid grid-cols-3 gap-4 mb-8">
        <div className="bg-white p-6 rounded-lg shadow">
          <div className="flex justify-between items-center mb-6">
            <h3 className="text-lg font-semibold">Booking by Platform</h3>
            <button className="text-gray-400">...</button>
          </div>
          <PieChart width={300} height={200}>
            <Pie
              data={bookingData.platforms}
              cx={150}
              cy={100}
              innerRadius={60}
              outerRadius={80}
              paddingAngle={5}
              dataKey="value"
            >
              {bookingData.platforms.map((entry, index) => (
                <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
              ))}
            </Pie>
          </PieChart>
          <div className="mt-4 space-y-2">
            {bookingData.platforms.map((platform, index) => (
              <div key={index} className="flex items-center justify-between">
                <div className="flex items-center">
                  <div className="w-3 h-3 rounded-full mr-2" style={{ backgroundColor: COLORS[index] }}></div>
                  <span>{platform.name}</span>
                </div>
                <span>{platform.value}%</span>
              </div>
            ))}
          </div>
        </div>

        <div className="bg-white p-6 rounded-lg shadow">
          <div className="flex justify-between items-center mb-6">
            <h3 className="text-lg font-semibold">Room Availability</h3>
            <button className="text-gray-400">...</button>
          </div>
          <div className="space-y-4">
            <div className="flex justify-between items-center">
              <div>
                <div className="text-4xl font-bold">286</div>
                <div className="text-gray-500">Occupied</div>
              </div>
              <div>
                <div className="text-4xl font-bold">87</div>
                <div className="text-gray-500">Reserved</div>
              </div>
              <div>
                <div className="text-4xl font-bold">32</div>
                <div className="text-gray-500">Available</div>
              </div>
              <div>
                <div className="text-4xl font-bold">13</div>
                <div className="text-gray-500">Not Ready</div>
              </div>
            </div>
          </div>
        </div>

        <div className="bg-white p-6 rounded-lg shadow">
          <div className="flex justify-between items-center mb-6">
            <h3 className="text-lg font-semibold">Tasks</h3>
            <button className="text-blue-500">+</button>
          </div>
          <div className="space-y-4">
            {tasks.map((task, index) => (
              <div key={index} className="flex items-center justify-between">
                <div>
                  <div className="text-sm text-gray-500">{task.date}</div>
                  <div>{task.title}</div>
                </div>
                <div className={`px-3 py-1 rounded-full text-sm ${
                  task.status === 'Completed' ? 'bg-purple-100 text-purple-800' :
                  task.status === 'In Progress' ? 'bg-blue-100 text-blue-800' :
                  'bg-gray-100 text-gray-800'
                }`}>
                  {task.status}
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>

      <div className="grid grid-cols-2 gap-4">
        <div className="bg-white p-6 rounded-lg shadow">
          <div className="flex justify-between items-center mb-6">
            <h3 className="text-lg font-semibold">Booking List</h3>
            <div className="flex gap-4">
              <input
                type="text"
                placeholder="Search guest, status, etc"
                className="px-4 py-2 border rounded-lg"
              />
              <button className="px-4 py-2 border rounded-lg">All Status ▼</button>
            </div>
          </div>
          <table className="w-full">
            <thead>
              <tr className="text-left text-gray-500">
                <th className="pb-4">Booking ID</th>
                <th className="pb-4">Guest Name</th>
                <th className="pb-4">Room</th>
                <th className="pb-4">Duration</th>
                <th className="pb-4">Check In/Out</th>
                <th className="pb-4">Status</th>
              </tr>
            </thead>
            <tbody>
              {bookings.map((booking) => (
                <tr key={booking.id} className="border-t">
                  <td className="py-4">{booking.id}</td>
                  <td className="py-4">{booking.guestName}</td>
                  <td className="py-4">Room {booking.roomNumber}</td>
                  <td className="py-4">{booking.duration}</td>
                  <td className="py-4">{booking.checkIn}</td>
                  <td className="py-4">
                    <span className={`px-3 py-1 rounded-full text-sm ${
                      booking.status === 'Checked-in' ? 'bg-green-100 text-green-800' : 'bg-blue-100 text-blue-800'
                    }`}>
                      {booking.status}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        <div className="bg-white p-6 rounded-lg shadow">
          <div className="flex justify-between items-center mb-6">
            <div>
              <h3 className="text-lg font-semibold">Recent Activities</h3>
              <div className="text-sm text-gray-500">1,242</div>
            </div>
            <button className="px-4 py-2 border rounded-lg">Popular ▼</button>
          </div>
          <div className="space-y-6">
            {recentActivities.map((activity, index) => (
              <div key={index} className="flex items-start gap-4">
                <div className="w-8 h-8 flex items-center justify-center rounded-full bg-gray-100">
                  {activity.icon}
                </div>
                <div className="flex-1">
                  <div className="text-sm text-gray-500">{activity.time}</div>
                  <div>{activity.title}</div>
                </div>
                <button className="text-gray-400">...</button>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
