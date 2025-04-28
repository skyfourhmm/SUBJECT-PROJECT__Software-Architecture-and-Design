// components/admin/ChatArea.js
import React from "react";

function ChatArea({ selectedChat, onSendMessage }) {
  return (
    <div className="flex-1 flex flex-col">
      {/* Chat Header */}
      <div className="p-4 border-b flex items-center justify-between">
        <div className="flex items-center gap-4">
          <img
            src={selectedChat.user.avatar}
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
                msg.isUser ? "bg-gray-100" : "bg-blue-500 text-white"
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
            onKeyPress={(e) => {
              if (e.key === "Enter" && e.target.value.trim()) {
                onSendMessage(e.target.value);
                e.target.value = "";
              }
            }}
          />
          <button className="text-xl text-gray-500">😊</button>
          <button
            onClick={() => {
              const input = document.querySelector('input[placeholder="Type a message..."]');
              if (input.value.trim()) {
                onSendMessage(input.value);
                input.value = "";
              }
            }}
            className="bg-blue-500 text-white rounded-full p-2"
          >
            ↗️
          </button>
        </div>
      </div>
    </div>
  );
}

export default ChatArea;