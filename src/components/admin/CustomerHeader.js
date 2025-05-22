import React from "react";

function CustomerHeader({
  searchQuery,
  setSearchQuery,
  onAddCustomer,
  handleEnterPress,
}) {
  return (
    <div className="flex justify-between items-center mb-6">
      <h1 className="text-xl font-semibold">Customer List</h1>
      <div className="flex gap-4">
        <input
          type="text"
          placeholder="Search phone, guest name"
          className="pl-10 pr-4 py-2 border rounded-lg w-64"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          onKeyDown={(e) => {
            if (e.key === "Enter") {
              // Gọi hàm xử lý khi nhấn Enter
              handleEnterPress(e);
            }
          }}
        />

        <button
          onClick={onAddCustomer}
          className="px-4 py-2 bg-blue-500 text-white rounded-lg flex items-center gap-2"
        >
          + Add Customer
        </button>
      </div>
    </div>
  );
}

export default CustomerHeader;
