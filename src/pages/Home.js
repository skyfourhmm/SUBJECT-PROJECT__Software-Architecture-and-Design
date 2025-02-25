import React from "react";
import Banner from "../assets/banner.png";

const Home = () => {
  return (
    <div className="bg-gray-100 font-sans relative">
      {/* Header */}
      <header
        className="relative bg-cover bg-center h-[500px] flex items-center justify-center text-white"
        style={{
          backgroundImage: `url(${Banner})`,
        }}
      >
        <div className="text-center">
          <h1 className="text-4xl font-bold">Helping Others</h1>
          <h2 className="text-5xl font-bold mt-2">LIVE & TRAVEL</h2>
          <p className="mt-4 text-lg">Explore the world on your own term</p>
        </div>
      </header>

      {/* Search */}
      <div className="max-w-5xl mx-auto bg-white p-6 rounded-lg shadow-md absolute left-1/2 transform -translate-x-1/2 -translate-y-1/2">
        <div className="flex border-b mb-4">
          <button className="flex-1 py-2 border-b-2 border-green-500 text-green-500 font-semibold flex items-center justify-center gap-2">
            ✈ Flights
          </button>
          <button className="flex-1 py-2 text-gray-500 flex items-center justify-center gap-2">
            🏨 Stays
          </button>
        </div>
        <div className="grid grid-cols-4 gap-4">
          <div className="border p-3 rounded-lg flex flex-col">
            <label className="text-gray-500 text-sm">From - To</label>
            <input
              type="text"
              className="outline-none"
              placeholder="Lahore - Karachi"
            />
          </div>
          <div className="border p-3 rounded-lg flex flex-col">
            <label className="text-gray-500 text-sm">Trip</label>
            <select className="outline-none">
              <option>Return</option>
              <option>One-way</option>
            </select>
          </div>
          <div className="border p-3 rounded-lg flex flex-col">
            <label className="text-gray-500 text-sm">Depart - Return</label>
            <input
              type="text"
              className="outline-none"
              placeholder="07 Nov 22 - 13 Nov 22"
            />
          </div>
          <div className="border p-3 rounded-lg flex flex-col">
            <label className="text-gray-500 text-sm">Passenger - Class</label>
            <input
              type="text"
              className="outline-none"
              placeholder="1 Passenger, Economy"
            />
          </div>
        </div>
        <div className="flex justify-between items-center mt-4">
          <button className="text-gray-500 text-sm">+ Add Promo Code</button>
          <button className="bg-green-500 text-white px-4 py-2 rounded-lg flex items-center gap-2">
            🚀 Show Flights
          </button>
        </div>
      </div>
    </div>
  );
};

export default Home;
