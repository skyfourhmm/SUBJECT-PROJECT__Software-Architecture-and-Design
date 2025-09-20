// components/admin/RestaurantHeader.js
import React from "react";

function RestaurantHeader({
  searchQuery,
  setSearchQuery,
  sortBy,
  setSortBy,
  selectedCategory,
  setSelectedCategory,
  onAddMenu,
}) {
  return (
    <div className="flex justify-between items-center mb-6">
      <h1 className="text-xl font-semibold">Restaurant</h1>
      <div className="flex gap-4">
        <input
          type="text"
          placeholder="Search food"
          className="pl-10 pr-4 py-2 border rounded-lg w-64"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
        />
        <select
          className="px-4 py-2 border rounded-lg text-gray-700"
          value={sortBy}
          onChange={(e) => setSortBy(e.target.value)}
        >
          <option value="popular">Popular</option>
          <option value="new">New</option>
          <option value="price_low">Price: Low to High</option>
          <option value="price_high">Price: High to Low</option>
        </select>
        <select
          className="px-4 py-2 border rounded-lg text-gray-700"
          value={selectedCategory}
          onChange={(e) => setSelectedCategory(e.target.value)}
        >
          <option value="All">All Type</option>
          <option value="Pizza">Pizza</option>
          <option value="Drinks">Drinks</option>
          <option value="Sushi">Sushi</option>
          <option value="Burger">Burger</option>
        </select>
        <button
          onClick={onAddMenu}
          className="px-4 py-2 bg-blue-500 text-white rounded-lg"
        >
          + Add Menu
        </button>
      </div>
    </div>
  );
}

export default RestaurantHeader;