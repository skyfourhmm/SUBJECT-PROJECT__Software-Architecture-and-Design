import { useState } from "react";
import { FaHotel, FaMap, FaMoneyBillWave, FaGlobe } from "react-icons/fa";
import { IoIosArrowDown } from "react-icons/io";

function Header() {
  const [currency, setCurrency] = useState("VND");
  const [language, setLanguage] = useState("Tiếng Việt (Việt Nam)");

  return (
    <nav className="bg-blue-500 text-white px-6 py-6 flex justify-between items-center">
      <h1 className="text-lg font-bold">DoubleQT HOTEL</h1>
      <div className="flex gap-4 items-center">
        <button className="flex items-center gap-2">
          <FaHotel /> Thông tin
        </button>
        <button className="flex items-center gap-2">
          <FaMap /> Bản đồ
        </button>
        <div className="relative">
          <button className="flex items-center gap-2">
            <FaMoneyBillWave /> {currency} <IoIosArrowDown />
          </button>
        </div>
        <div className="relative">
          <button className="flex items-center gap-2">
            <FaGlobe /> {language} <IoIosArrowDown />
          </button>
        </div>
      </div>
    </nav>
  );
}

export default Header;
