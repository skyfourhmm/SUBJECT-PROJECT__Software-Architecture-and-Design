import { useEffect, useState } from "react";
import BillsHeader from "../../components/admin/BillHeader";
import BillTable from "../../components/admin/BillTable";
import { getAllPayments } from "../../api/payment";
import { getCustomerById } from "../../api/user";
import React, { useRef } from "react";
import { useReactToPrint } from "react-to-print";
import PrintableBill from "../../components/admin/PrintableBill";

function Bills() {
  const [searchQuery, setSearchQuery] = useState("");
  const [listBills, setListBills] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [bill, setBill] = useState({});

  useEffect(() => {
    const fetchBills = async () => {
      try {
        const bills = await getAllPayments(); // Lấy tất cả hóa đơn

        const billsWithCustomer = await Promise.all(
          bills.map(async (bill) => {
            try {
              const customer = await getCustomerById(bill?.maKhachHang);
              return {
                ...bill,
                khachHang: customer, // Thêm thông tin khách hàng vào mỗi bill
              };
            } catch (err) {
              console.error(
                `❌ Lỗi khi lấy khách hàng ${bill?.maKhachHang}:`,
                err
              );
              return {
                ...bill,
                khachHang: null, // hoặc gán mặc định nếu lỗi
              };
            }
          })
        );

        setListBills(billsWithCustomer); // Cập nhật listBills
      } catch (error) {
        console.error("❌ Lỗi khi lấy hóa đơn:", error);
      }
    };

    fetchBills();
  }, []);

  const handlePrint = (booking) => {
    // Logic to handle printing the bill
    setShowModal(true);
    setBill(booking);
  };

  //   in hóa đơn
  const componentRef = useRef();
  const [selectedBill, setSelectedBill] = useState(null);

  const handlePrintBill = useReactToPrint({
    content: () => componentRef.current,
  });

  const handlePrintClick = (bill) => {
    setSelectedBill(bill);
    setTimeout(() => {
      handlePrintBill(); // gọi in sau khi setSelectedBill xong
    }, 100); // delay ngắn để đảm bảo render xong
  };

  return (
    <div className="p-6">
      <BillsHeader searchQuery={searchQuery} setSearchQuery={setSearchQuery} />

      <BillTable listBills={listBills} onPrint={handlePrint} />

      {showModal && (
        <div className="fixed inset-0 z-50 bg-black bg-opacity-50 flex justify-center items-center">
          <div className="bg-white w-full max-w-2xl rounded-xl shadow-lg p-6 relative">
            {/* Nút đóng */}
            <button
              onClick={() => setShowModal(false)}
              className="absolute top-3 right-3 text-gray-600 hover:text-gray-800 text-xl"
            >
              ✕
            </button>

            <h2 className="text-2xl font-bold text-center mb-6">
              Chi tiết hóa đơn
            </h2>

            <div className="grid grid-cols-2 gap-4 text-base">
              <p>
                <span className="font-semibold">Mã hóa đơn:</span>{" "}
                {bill.maHoaDon}
              </p>
              <p>
                <span className="font-semibold">Trạng thái:</span>{" "}
                {bill.trangThai}
              </p>
              <p>
                <span className="font-semibold">Giá tiền:</span>{" "}
                {bill.giaTien.toLocaleString()} VND
              </p>
              <p>
                <span className="font-semibold">Ngày tạo:</span>{" "}
                {bill.thoiGianTao}
              </p>
              <p>
                <span className="font-semibold">Thời gian thanh toán:</span>{" "}
                {bill.thoiGianThanhToan || "Chưa thanh toán"}
              </p>
              <p>
                <span className="font-semibold">Khách hàng:</span>{" "}
                {bill.khachHang?.hoTen || "N/A"}
              </p>
              <p>
                <span className="font-semibold">Nhân viên:</span>{" "}
                {bill.maNhanVien || "N/A"}
              </p>
            </div>

            <div className="mt-6 flex justify-between">
              <button
                onClick={() => {
                  // Gọi hàm in tại đây
                  handlePrintClick(bill);
                }}
                className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
              >
                In hóa đơn
              </button>

              <button
                onClick={() => setShowModal(false)}
                className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
              >
                Đóng
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Thành phần ẩn để in */}
      <div style={{ display: "none" }}>
        <PrintableBill ref={componentRef} billInfo={selectedBill} />
      </div>
    </div>
  );
}

export default Bills;
