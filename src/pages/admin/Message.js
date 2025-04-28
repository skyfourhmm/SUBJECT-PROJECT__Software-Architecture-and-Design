
import React, { useState } from "react";

function Message() {
  const [messages] = useState([
    {
      id: 1,
      user: {
        name: "Alice Johnson",
        avatar: "https://i.pravatar.cc/150?img=1"
      },
      message: "Requesting late check-out for Room 305.",
      time: "09:15 AM",
      status: "new"
    },
    {
      id: 2,
      user: {
        name: "Michael Brown",
        avatar: "https://i.pravatar.cc/150?img=2"
      },
      message: "Reports air conditioning issue in his room.",
      time: "09:30 AM",
      status: "new"
    },
    {
      id: 3,
      user: {
        name: "Emily Davis",
        avatar: "https://i.pravatar.cc/150?img=3"
      },
      message: "Requests confirmation for airport pickup.",
      time: "09:45 AM",
      status: "new"
    }
  ]);

  const [selectedChat] = useState({
    user: {
      name: "Alice Johnson",
      status: "last seen recently"
    },
    messages: [
      {
        text: "Can I request a late check-out for Room 305?",
        time: "9:15 PM",
        isUser: true
      },
      {
        text: "Hi Alice, we can accommodate a late check-out. How late would you like to stay?",
        time: "9:20 AM",
        isUser: false
      },
      {
        text: "I was hoping to stay until 2 PM. Is that possible?",
        time: "9:22 AM",
        isUser: true
      },
      {
        text: "Let me check the availability for Room 305. One moment, please.",
        time: "9:25 AM",
        isUser: false
      },
      {
        text: "Good news, Alice! We can extend your check-out time to 2 PM.",
        time: "9:30 AM",
        isUser: false
      },
      {
        text: "Thank you so much! That really helps.",
        time: "9:32 AM",
        isUser: true
      },
      {
        text: "You're welcome! If you need anything else, feel free to let us know.",
        time: "9:35 AM",
        isUser: false
      }
    ]
  });

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-xl font-semibold">Message</h1>
        <div className="flex gap-4">
          <div className="relative">
            <input
              type="text"
              placeholder="Search message"
              className="pl-10 pr-4 py-2 border rounded-lg w-64"
            />
            <span className="absolute left-3 top-2.5">🔍</span>
          </div>
        </div>
      </div>

      <div className="bg-white rounded-lg shadow flex h-[calc(100vh-160px)]">
        {/* Message List */}
        <div className="w-1/3 border-r">
          <div className="overflow-y-auto h-full">
            {messages.map((msg) => (
              <div
                key={msg.id}
                className="p-4 border-b hover:bg-gray-50 cursor-pointer flex items-center gap-4"
              >
                <img
                  src={msg.user.avatar}
                  alt={msg.user.name}
                  className="w-12 h-12 rounded-full"
                />
                <div className="flex-1">
                  <div className="flex justify-between items-start">
                    <h3 className="font-medium">{msg.user.name}</h3>
                    <span className="text-sm text-gray-500">{msg.time}</span>
                  </div>
                  <p className="text-sm text-gray-600 truncate">{msg.message}</p>
                </div>
                {msg.status === "new" && (
                  <span className="w-2 h-2 bg-red-500 rounded-full"></span>
                )}
              </div>
            ))}
          </div>
        </div>

        {/* Chat Area */}
        <div className="flex-1 flex flex-col">
          {/* Chat Header */}
          <div className="p-4 border-b flex items-center justify-between">
            <div className="flex items-center gap-4">
              <img
                src="https://i.pravatar.cc/150?img=1"
                alt={selectedChat.user.name}
                className="w-10 h-10 rounded-full"
              />
              <div>
                <h3 className="font-medium">{selectedChat.user.name}</h3>
                <p className="text-sm text-gray-500">{selectedChat.user.status}</p>
              </div>
            </div>
            <button className="text-xl">⋮</button>
          </div>

          {/* Messages */}
          <div className="flex-1 overflow-y-auto p-4 space-y-4">
            {selectedChat.messages.map((msg, index) => (
              <div
                key={index}
                className={`flex ${msg.isUser ? "justify-start" : "justify-end"}`}
              >
                <div
                  className={`max-w-[70%] p-3 rounded-lg ${
                    msg.isUser
                      ? "bg-gray-100"
                      : "bg-blue-500 text-white"
                  }`}
                >
                  <p>{msg.text}</p>
                  <div
                    className={`text-xs mt-1 ${
                      msg.isUser ? "text-gray-500" : "text-blue-100"
                    }`}
                  >
                    {msg.time}
                  </div>
                </div>
              </div>
            ))}
          </div>

          {/* Input Area */}
          <div className="p-4 border-t">
            <div className="flex items-center gap-4">
              <button className="text-xl text-gray-500">📎</button>
              <input
                type="text"
                placeholder="Type a message..."
                className="flex-1 border rounded-full px-4 py-2"
              />
              <button className="text-xl text-gray-500">😊</button>
              <button className="bg-blue-500 text-white rounded-full p-2">
                ↗️
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Message;
