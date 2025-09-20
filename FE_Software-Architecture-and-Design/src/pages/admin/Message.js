import React, { useState } from "react";
import MessageHeader from "../../components/admin/MessageHeader";
import MessageList from "../../components/admin/MessageList";
import ChatArea from "../../components/admin/ChatArea";

function Message() {
  const initialMessages = [
    {
      id: 1,
      user: {
        name: "Alice Johnson",
        avatar: "https://i.pravatar.cc/150?img=1",
      },
      message: "Requesting late check-out for Room 305.",
      time: "09:15 AM",
      status: "new",
    },
    {
      id: 2,
      user: {
        name: "Michael Brown",
        avatar: "https://i.pravatar.cc/150?img=2",
      },
      message: "Reports air conditioning issue in his room.",
      time: "09:30 AM",
      status: "new",
    },
    {
      id: 3,
      user: {
        name: "Emily Davis",
        avatar: "https://i.pravatar.cc/150?img=3",
      },
      message: "Requests confirmation for airport pickup.",
      time: "09:45 AM",
      status: "new",
    },
  ];

  const [messages] = useState(initialMessages);
  const [searchQuery, setSearchQuery] = useState("");
  const [selectedChat, setSelectedChat] = useState({
    user: {
      name: "Alice Johnson",
      avatar: "https://i.pravatar.cc/150?img=1",
      status: "last seen recently",
    },
    messages: [
      {
        text: "Can I request a late check-out for Room 305?",
        time: "9:15 PM",
        isUser: true,
      },
      {
        text: "Hi Alice, we can accommodate a late check-out. How late would you like to stay?",
        time: "9:20 AM",
        isUser: false,
      },
      {
        text: "I was hoping to stay until 2 PM. Is that possible?",
        time: "9:22 AM",
        isUser: true,
      },
      {
        text: "Let me check the availability for Room 305. One moment, please.",
        time: "9:25 AM",
        isUser: false,
      },
      {
        text: "Good news, Alice! We can extend your check-out time to 2 PM.",
        time: "9:30 AM",
        isUser: false,
      },
      {
        text: "Thank you so much! That really helps.",
        time: "9:32 AM",
        isUser: true,
      },
      {
        text: "You're welcome! If you need anything else, feel free to let us know.",
        time: "9:35 AM",
        isUser: false,
      },
    ],
  });

  const filteredMessages = messages.filter(
    (msg) =>
      msg.user.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
      msg.message.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const handleSelectMessage = (msg) => {
    // Cập nhật selectedChat dựa trên tin nhắn được chọn
    // Hiện tại chỉ mock dữ liệu cho Alice Johnson, có thể mở rộng với API
    setSelectedChat({
      user: {
        name: msg.user.name,
        avatar: msg.user.avatar,
        status: "last seen recently",
      },
      messages: selectedChat.messages, // Giữ nguyên dữ liệu mock
    });
  };

  const handleSendMessage = (text) => {
    // Logic để gửi tin nhắn mới
    console.log("Send message:", text);
    // Ví dụ: Thêm tin nhắn vào selectedChat (cần API thực tế)
    setSelectedChat((prev) => ({
      ...prev,
      messages: [
        ...prev.messages,
        {
          text,
          time: new Date().toLocaleTimeString([], {
            hour: "2-digit",
            minute: "2-digit",
          }),
          isUser: false,
        },
      ],
    }));
  };

  return (
    <div className="p-6">
      <MessageHeader
        searchQuery={searchQuery}
        setSearchQuery={setSearchQuery}
      />
      <div className="bg-white rounded-lg shadow flex h-[calc(100vh-160px)]">
        <MessageList
          messages={filteredMessages}
          onSelectMessage={handleSelectMessage}
        />
        {selectedChat && (
          <ChatArea
            selectedChat={selectedChat}
            onSendMessage={handleSendMessage}
          />
        )}
      </div>
    </div>
  );
}

export default Message;