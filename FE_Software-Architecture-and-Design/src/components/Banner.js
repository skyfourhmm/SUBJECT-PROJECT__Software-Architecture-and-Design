import { useState, useEffect } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

import { FaRegCalendarAlt } from "react-icons/fa";

function Banner() {
  const [checkInDate, setCheckInDate] = useState(new Date());
  const [checkOutDate, setCheckOutDate] = useState(() => {
    const tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    return tomorrow;
  });
  const [guests, setGuests] = useState(1);

  useEffect(() => {
    if (checkInDate >= checkOutDate) {
      const nextDay = new Date(checkInDate);
      nextDay.setDate(nextDay.getDate() + 1);
      setCheckOutDate(nextDay);
    }
  }, [checkInDate]);

  return (
    <div
      className="relative w-full h-[400px] bg-cover bg-center"
      style={{
        backgroundImage:
          "url('https://id.bluejaypms.com/Uploads/5397/a7d48468-5362-4d35-a3de-390cbd3d9026.jpg')",
      }}
    >
      <div className="absolute inset-0 flex justify-center items-center translate-y-20">
        <div className="flex gap-4 bg-black bg-opacity-50 p-4 rounded-md">
          <div className="flex flex-col">
            <label className="text-white mb-2">Nhận phòng</label>
            <DatePicker
              showIcon
              selected={checkInDate}
              onChange={(date) => setCheckInDate(date)}
              dateFormat="dd/MM/yyyy"
              className="p-2 rounded-md"
              icon={<FaRegCalendarAlt />}
            />
          </div>
          <div className="flex flex-col">
            <label className="text-white mb-2">Trả phòng</label>
            <DatePicker
              showIcon
              selected={checkOutDate}
              onChange={(date) => setCheckOutDate(date)}
              dateFormat="dd/MM/yyyy"
              className="p-2 rounded-md"
              minDate={checkInDate}
              icon={<FaRegCalendarAlt />}
            />
          </div>
          <div className="flex flex-col">
            <label className="text-white mb-2">Khách</label>
            <input
              type="number"
              value={guests}
              min="1"
              onChange={(e) => setGuests(e.target.value)}
              className="p-2 rounded-md"
            />
          </div>
          <div className="flex flex-col justify-end">
            <button className="bg-yellow-500 px-4 py-2 rounded-md hover:bg-yellow-400">
              Kiểm tra
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Banner;
