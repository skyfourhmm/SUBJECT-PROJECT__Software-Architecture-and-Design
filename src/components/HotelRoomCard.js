import React from "react";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { FaBed, FaUser, FaCheckCircle } from "react-icons/fa";

const HotelRoomCard = () => {
  return (
    <Card className="flex flex-row p-4 shadow-md border rounded-lg w-full max-w-2xl">
      <div className="relative w-1/3">
        <img
          src="/path-to-your-image.jpg"
          alt="Deluxe Room"
          className="rounded-lg w-full h-full object-cover"
        />
        <div className="absolute bottom-0 left-0 bg-black bg-opacity-50 text-white p-2 text-sm cursor-pointer">
          📷 XEM HÌNH ẢNH
        </div>
      </div>
      <CardContent className="w-2/3 flex flex-col justify-between pl-4">
        <div>
          <h2 className="text-lg font-semibold text-blue-600">
            Deluxe Room with City View
          </h2>
          <p className="flex items-center text-gray-600 mt-1">
            <FaBed className="mr-2" /> 1 Giường đôi cỡ lớn.
          </p>
          <h3 className="mt-2 text-md font-medium">Gói cơ bản</h3>
          <p className="flex items-center text-gray-600 mt-1">
            <FaUser className="mr-2" /> 2 Người lớn, 1 Trẻ em
          </p>
          <p className="flex items-center text-blue-600 mt-1">
            <FaCheckCircle className="mr-2" /> Đã bao gồm bữa sáng
          </p>
        </div>
        <div className="flex items-center justify-between mt-3">
          <span className="text-red-600 text-xl font-bold">5.000.000 đ</span>
          <Button className="bg-blue-600 text-white px-4 py-2 rounded-lg">
            Chọn
          </Button>
        </div>
      </CardContent>
    </Card>
  );
};

export default HotelRoomCard;
