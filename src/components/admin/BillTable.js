function BillTable({ listBills, onPrint }) {
  console.log("List of bills:", listBills);
  return (
    <div className="bg-white rounded-lg shadow">
      <table className="w-full">
        <thead className="bg-gray-50">
          <tr>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Ma Hoa Don
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Gia Phong
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              khach Hang
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Check-In/Out
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Status
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Action
            </th>
          </tr>
        </thead>
        <tbody className="divide-y divide-gray-200">
          {listBills.map((booking) => (
            <tr key={booking.maHoaDon} className="hover:bg-gray-50">
              <td className="px-6 py-4">
                <div>
                  {/* <div className="font-medium">{booking?.khachHang?.hoTen}</div> */}
                  <div className="text-sm text-gray-500">
                    {booking.maHoaDon}
                  </div>
                </div>
              </td>
              <td className="px-6 py-4">{booking?.giaTien}</td>
              <td className="px-6 py-4">{booking?.khachHang?.hoTen}</td>
              <td className="px-6 py-4">{booking.thoiGianTao}</td>
              <td className="px-6 py-4">
                <span
                  className={`px-3 py-1 rounded-full text-sm ${
                    booking.trangThai === "Đã đặt"
                      ? "bg-green-100 text-green-800"
                      : booking.trangThai === "Đã Thanh Toán"
                      ? "bg-blue-100 text-blue-800"
                      : "bg-red-100 text-red-800"
                  }`}
                >
                  {booking.trangThai}
                </span>
              </td>
              <td className="px-6 py-4">
                <div className="flex gap-2">
                  {booking.trangThai !== "Đã đặt" && (
                    <button
                      onClick={() => onPrint(booking)}
                      className="p-1 hover:bg-gray-100 rounded"
                    >
                      📝
                    </button>
                  )}
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default BillTable;
