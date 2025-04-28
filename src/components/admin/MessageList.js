// components/admin/MessageList.js
import React from "react";

function MessageList({ messages, onSelectMessage }) {
  return (
    <div className="w-1/3 border-r">
      <div className="overflow-y-auto h-full">
        {messages.map((msg) => (
          <div
            key={msg.id}
            onClick={() => onSelectMessage(msg)}
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
  );
}

export default MessageList;