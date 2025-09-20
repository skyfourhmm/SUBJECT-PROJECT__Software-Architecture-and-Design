// components/admin/MessageHeader.js
import React from "react";

function MessageHeader({ searchQuery, setSearchQuery }) {
  return (
    <div className="flex justify-between items-center mb-6">
      <h1 className="text-xl font-semibold">Message</h1>
      <div className="flex gap-4">
        <div className="relative">
          <input
            type="text"
            placeholder="Search message"
            className="pl-10 pr-4 py-2 border rounded-lg w-64"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
          />
          <span className="absolute left-3 top-2.5">🔍</span>
        </div>
      </div>
    </div>
  );
}

export default MessageHeader;