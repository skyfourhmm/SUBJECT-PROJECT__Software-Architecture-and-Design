// components/admin/MenuItems.js
import React from "react";

function MenuItems({ items }) {
  return (
    <div className="grid grid-cols-3 gap-6 mb-8">
      {items.map((item) => (
        <div key={item.id} className="bg-white p-4 rounded-lg shadow">
          <img
            src={item.image}
            alt={item.name}
            className="w-full h-48 object-cover rounded-lg mb-4"
          />
          <div className="flex justify-between items-start">
            <div>
              <h3 className="font-semibold">{item.name}</h3>
              <div className="flex items-center gap-1 text-sm text-gray-600">
                <span>⭐</span>
                <span>{item.rating}</span>
              </div>
            </div>
            <div className="text-right">
              <div className="text-gray-500">Price</div>
              <div className="font-semibold">${item.price}</div>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}

export default MenuItems;