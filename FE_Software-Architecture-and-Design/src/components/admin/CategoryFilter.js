// components/admin/CategoryFilter.js
import React from "react";

function CategoryFilter({ selectedCategory, setSelectedCategory }) {
  const categories = [
    { name: "All", icon: "🍽️" },
    { name: "Pizza", icon: "🍕" },
    { name: "Drinks", icon: "🥤" },
    { name: "Sushi", icon: "🍣" },
    { name: "Burger", icon: "🍔" },
  ];

  return (
    <div className="flex gap-4 mb-6">
      {categories.map((category) => (
        <button
          key={category.name}
          onClick={() => setSelectedCategory(category.name)}
          className={`px-6 py-2 rounded-full flex items-center gap-2 ${
            selectedCategory === category.name
              ? "bg-blue-100 text-blue-600"
              : "hover:bg-gray-100"
          }`}
        >
          <span>{category.icon}</span>
          {category.name}
        </button>
      ))}
    </div>
  );
}

export default CategoryFilter;