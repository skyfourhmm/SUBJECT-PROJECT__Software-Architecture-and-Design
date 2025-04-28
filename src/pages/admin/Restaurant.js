import React, { useState } from "react";

function Restaurant() {
  const [searchQuery, setSearchQuery] = useState("");
  const [sortBy, setSortBy] = useState("popular");
  const [selectedCategory, setSelectedCategory] = useState("All");

  const menuItems = [
    {
      id: 1,
      name: "Grilled Salmon with Lemon",
      rating: 4.7,
      price: 25.45,
      image: "https://images.unsplash.com/photo-1485921325833-c519f76c4927?q=80&w=2024&auto=format&fit=crop",
      category: "All"
    },
    {
      id: 2,
      name: "Classic Caesar Salad",
      rating: 4.7,
      price: 25.45,
      image: "https://images.unsplash.com/photo-1546793665-c74683f339c1?q=80&w=1974&auto=format&fit=crop",
      category: "Pizza"
    },
    {
      id: 3, 
      name: "Beef Tenderloin Steak",
      rating: 4.7,
      price: 25.45,
      image: "https://images.unsplash.com/photo-1600891964092-4316c288032e?q=80&w=2070&auto=format&fit=crop",
      category: "Drinks"
    }
  ];

  const orders = [
    {
      id: "#45565B53542",
      name: "Egg Aulete",
      price: 45.45,
      location: "2215A Dale St, San Jose, South Dakota 58479"
    },
    {
      id: "#45565B55576",
      name: "Sushi Blast",
      price: 25.45,
      location: "6391 Eye St, Ozona Delaware 12599"
    }
  ];

  const filteredMenuItems = menuItems.filter(item => 
    item.name.toLowerCase().includes(searchQuery.toLowerCase()) && 
    item.category.includes(selectedCategory)
  );


  return (
    <div className="p-6">
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
          <button className="px-4 py-2 bg-blue-500 text-white rounded-lg">
            + Add Menu
          </button>
        </div>
      </div>

      <div className="flex gap-4 mb-6">
        <button className="px-6 py-2 bg-blue-100 text-blue-600 rounded-full flex items-center gap-2">
          <span>🍽️</span> All
        </button>
        <button className="px-6 py-2 hover:bg-gray-100 rounded-full flex items-center gap-2">
          <span>🍕</span> Pizza
        </button>
        <button className="px-6 py-2 hover:bg-gray-100 rounded-full flex items-center gap-2">
          <span>🥤</span> Drinks
        </button>
        <button className="px-6 py-2 hover:bg-gray-100 rounded-full flex items-center gap-2">
          <span>🍣</span> Sushi
        </button>
        <button className="px-6 py-2 hover:bg-gray-100 rounded-full flex items-center gap-2">
          <span>🍔</span> Burger
        </button>
      </div>

      <div className="grid grid-cols-3 gap-6 mb-8">
        {filteredMenuItems.map((item) => (
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

      <div className="bg-white rounded-lg shadow p-6">
        <h2 className="text-lg font-semibold mb-4">Orders</h2>
        <div className="flex gap-4 mb-6">
          <button className="px-4 py-1 bg-blue-500 text-white rounded-full">Active</button>
          <button className="px-4 py-1 text-gray-600 hover:bg-gray-100 rounded-full">Past</button>
          <button className="px-4 py-1 text-gray-600 hover:bg-gray-100 rounded-full">Canceled</button>
        </div>

        <div className="space-y-4">
          {orders.map((order) => (
            <div key={order.id} className="flex items-center justify-between p-4 border rounded-lg">
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
    </div>
  );
}

export default Restaurant;