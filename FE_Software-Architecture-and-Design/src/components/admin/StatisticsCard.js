// components/admin/StatisticsCard.js
import React from "react";

function StatisticsCard({ title, value, change, isPrimary }) {
  return (
    <div
      className={`p-4 rounded-lg shadow ${
        isPrimary ? "bg-blue-500 text-white" : "bg-white"
      }`}
    >
      <div className="mb-4">{title}</div>
      <div className="text-4xl font-bold">{value}</div>
      <div
        className={`text-sm mt-2 ${
          isPrimary ? "text-blue-100" : "text-gray-500"
        }`}
      >
        {change}
      </div>
    </div>
  );
}

export default StatisticsCard;