// components/admin/OrdersSection.js
import React, { useState } from "react";

function OrdersSection({ orders }) {
  const [activeTab, setActiveTab] = useState("Active");

  return (
    <div className="bg-white rounded-lg shadow p-6">
      <h2 className="text-lg font-semibold mb-4">Orders</h2>
      <div className="flex gap-4 mb-6">
        {["Active", "Past", "Canceled"].map((tab) => (
          <button
            key={tab}
            onClick={() => setActiveTab(tab)}
            className={`px-4 py-1 rounded-full ${
              activeTab === tab
                ? "bg-blue-500 text-white"
                : "text-gray-600 hover:bg-gray-100"
            }`}
          >
            {tab}
          </button>
        ))}
      </div>
      <div className="space-y-4">
        {orders.map((order) => (
          <div
            key={order.id}
            className="flex items-center justify-between p-4 border rounded-lg"
          >
            <div className="flex items-center gap-4">
              <div className="w-16 h-16 bg-gray-200 rounded-lg"></div>
              <div>
                <div className="text-gray-500 text-sm">{order.id}</div>
                <div className="font-semibold">{order.name}</div>
                <div className="text-sm text-gray-500">{order.location}</div>
              </div>
            </div>
            <div className="font-semibold">${order.price}</div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default OrdersSection;