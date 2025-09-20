import React, { useState } from "react";
import RestaurantHeader from "../../components/admin/RestaurantHeader";
import CategoryFilter from "../../components/admin/CategoryFilter";
import MenuItems from "../../components/admin/MenuItems";
import OrdersSection from "../../components/admin/OrdersSection";

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
      image:
        "https://images.unsplash.com/photo-1485921325833-c519f76c4927?q=80&w=2024&auto=format&fit=crop",
      category: "All",
    },
    {
      id: 2,
      name: "Classic Caesar Salad",
      rating: 4.7,
      price: 25.45,
      image:
        "https://images.unsplash.com/photo-1546793665-c74683f339c1?q=80&w=1974&auto=format&fit=crop",
      category: "Pizza",
    },
    {
      id: 3,
      name: "Beef Tenderloin Steak",
      rating: 4.7,
      price: 25.45,
      image:
        "https://images.unsplash.com/photo-1600891964092-4316c288032e?q=80&w=2070&auto=format&fit=crop",
      category: "Drinks",
    },
  ];

  const orders = [
    {
      id: "#45565B53542",
      name: "Egg Aulete",
      price: 45.45,
      location: "2215A Dale St, San Jose, South Dakota 58479",
    },
    {
      id: "#45565B55576",
      name: "Sushi Blast",
      price: 25.45,
      location: "6391 Eye St, Ozona Delaware 12599",
    },
  ];

  const filteredMenuItems = menuItems.filter(
    (item) =>
      item.name.toLowerCase().includes(searchQuery.toLowerCase()) &&
      (selectedCategory === "All" || item.category === selectedCategory)
  );

  // Sắp xếp món ăn theo sortBy
  const sortedMenuItems = [...filteredMenuItems].sort((a, b) => {
    switch (sortBy) {
      case "price_low":
        return a.price - b.price;
      case "price_high":
        return b.price - a.price;
      case "new":
        return b.id - a.id;
      case "popular":
      default:
        return 0;
    }
  });

  const handleAddMenu = () => {
    // Logic để thêm món ăn mới (có thể mở modal hoặc chuyển hướng)
    console.log("Add new menu item");
  };

  return (
    <div className="p-6">
      <RestaurantHeader
        searchQuery={searchQuery}
        setSearchQuery={setSearchQuery}
        sortBy={sortBy}
        setSortBy={setSortBy}
        selectedCategory={selectedCategory}
        setSelectedCategory={setSelectedCategory}
        onAddMenu={handleAddMenu}
      />
      <CategoryFilter
        selectedCategory={selectedCategory}
        setSelectedCategory={setSelectedCategory}
      />
      <MenuItems items={sortedMenuItems} />
      <OrdersSection orders={orders} />
    </div>
  );
}

export default Restaurant;