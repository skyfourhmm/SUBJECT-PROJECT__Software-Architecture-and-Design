import React, { useEffect, useState } from "react";
import CustomerTable from "./CustomerTable";
import { getCustomerByPhone, getListCustomer, register } from "../../api/user";
import CustomerHeader from "./CustomerHeader";

function CustomerList() {
  const [customerList, setCustomerList] = useState([]);
  useEffect(() => {
    const fetchCustomerData = async () => {
      const response = await getListCustomer();
      setCustomerList(response);
    };
    fetchCustomerData();
  }, []);

  const [bookings, setBookings] = useState([
    {
      id: "LG-B00113",
      guestName: "John Miller",
      roomType: "Deluxe 101",
      request: "Late Check-Out",
      nights: "3 nights",
      date: "July 10 - 13, 2023",
      status: "Confirmed",
    },
    {
      id: "LG-B00114",
      guestName: "Emily Davis",
      roomType: "Standard 202",
      request: "None",
      nights: "2 nights",
      date: "July 9 - 11, 2023",
      status: "Confirmed",
    },
    {
      id: "LG-B00115",
      guestName: "Liam Thompson",
      roomType: "Suite 203",
      request: "Extra Pillows",
      nights: "5 nights",
      date: "July 8 - 13, 2023",
      status: "Pending",
    },
  ]);

  const [searchQuery, setSearchQuery] = useState("");
  const [showAddModal, setShowAddModal] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [selectedBooking, setSelectedBooking] = useState(null);
  const [newBooking, setNewBooking] = useState({
    tenDangNhap: "",
    matKhau: "",
    loaiNguoiDung: "",
    hoTen: "",
    gioiTinh: "",
    ngaySinh: "",
    diaChi: "",
    soDienThoai: "",
    cccd: "",
  });

  const handleAddCustomer = () => {
    setNewBooking({
      tenDangNhap: "",
      matKhau: "",
      loaiNguoiDung: "",
      hoTen: "",
      gioiTinh: "",
      ngaySinh: "",
      diaChi: "",
      soDienThoai: "",
      cccd: "",
    });
    setShowAddModal(true);
  };

  const handleSaveNewBooking = async () => {
    if (
      newBooking.hoTen &&
      newBooking.gioiTinh &&
      newBooking.ngaySinh &&
      newBooking.soDienThoai
    ) {
      try {
        // Gọi API đăng ký
        await register({
          tenDangNhap: "",
          matKhau: "000000",
          loaiNguoiDung: "khachHang",
          hoTen: newBooking.hoTen,
          gioiTinh: newBooking.gioiTinh,
          ngaySinh: newBooking.ngaySinh,
          diaChi: newBooking.diaChi || null,
          soDienThoai: newBooking.soDienThoai,
          cccd: newBooking.cccd || null,
        });

        // Gọi lại API danh sách khách hàng để cập nhật bảng
        const updatedCustomerList = await getListCustomer();
        setCustomerList(updatedCustomerList);

        // Đóng modal và reset form
        setShowAddModal(false);
        setNewBooking({
          tenDangNhap: "",
          matKhau: "",
          hoTen: "",
          gioiTinh: "",
          ngaySinh: "",
          diaChi: "",
          soDienThoai: "",
          cccd: "",
          ghiChu: "",
        });
      } catch (error) {
        alert("Lỗi khi đăng ký khách hàng: " + error.message);
        console.error(error);
      }
    } else {
      alert("Vui lòng nhập đầy đủ các trường bắt buộc!");
    }
  };

  const handleEdit = (booking) => {
    setSelectedBooking(booking);
    setNewBooking(booking);
    setShowEditModal(true);
  };

  const handleSaveEdit = () => {
    if (
      newBooking.guestName &&
      newBooking.roomType &&
      newBooking.nights &&
      newBooking.date
    ) {
      setBookings(
        bookings.map((b) => (b.id === newBooking.id ? newBooking : b))
      );
      setShowEditModal(false);
      setNewBooking({
        id: "",
        guestName: "",
        roomType: "",
        request: "",
        nights: "",
        date: "",
        status: "Pending",
      });
    } else {
      alert("Please fill in all required fields.");
    }
  };

  const handleEnterPress = async (e) => {
    const phone = searchQuery.trim();

    // Kiểm tra xem chỉ gồm số và ít nhất 6 chữ số (hoặc tùy theo yêu cầu)
    const isValidPhone = /^\d{6,}$/.test(phone);

    if (!isValidPhone) {
      alert("Vui lòng nhập số điện thoại hợp lệ (ít nhất 6 chữ số).");
      return;
    }

    try {
      const res = await getCustomerByPhone(phone);
      setCustomerList([res]);
    } catch (error) {
      alert("Không tìm thấy khách hàng hoặc lỗi kết nối.");
      console.error(error);
    }
  };

  return (
    <div className="p-6">
      <CustomerHeader
        searchQuery={searchQuery}
        setSearchQuery={setSearchQuery}
        onAddCustomer={handleAddCustomer}
        handleEnterPress={handleEnterPress}
      />
      <CustomerTable customerList={customerList} onEdit={handleEdit} />

      {/* Modal for Add Booking */}
      {showAddModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white p-6 rounded-lg shadow-lg w-96 max-h-[90vh] overflow-auto">
            <h2 className="text-lg font-semibold mb-4">Thêm Người Dùng Mới</h2>
            <div className="space-y-4">
              <input
                type="text"
                placeholder="Họ và tên"
                value={newBooking.hoTen}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, hoTen: e.target.value })
                }
                className="w-full p-2 border rounded"
              />

              <select
                value={newBooking.gioiTinh}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, gioiTinh: e.target.value })
                }
                className="w-full p-2 border rounded"
              >
                <option value="">Chọn giới tính</option>
                <option value="Nam">Nam</option>
                <option value="Nữ">Nữ</option>
                <option value="Khác">Khác</option>
              </select>

              <input
                type="date"
                placeholder="Ngày sinh"
                value={newBooking.ngaySinh}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, ngaySinh: e.target.value })
                }
                className="w-full p-2 border rounded"
              />

              <input
                type="text"
                placeholder="Địa chỉ"
                value={newBooking.diaChi}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, diaChi: e.target.value })
                }
                className="w-full p-2 border rounded"
              />

              <input
                type="tel"
                placeholder="Số điện thoại (bắt buộc)"
                value={newBooking.soDienThoai}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, soDienThoai: e.target.value })
                }
                className="w-full p-2 border rounded"
              />

              <input
                type="text"
                placeholder="Số CCCD (bắt buộc)"
                value={newBooking.cccd}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, cccd: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
            </div>
            <div className="flex gap-4 mt-4">
              <button
                onClick={handleSaveNewBooking}
                className="px-4 py-2 bg-blue-500 text-white rounded"
              >
                Lưu
              </button>
              <button
                onClick={() => setShowAddModal(false)}
                className="px-4 py-2 border rounded"
              >
                Hủy
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Modal for Edit Booking */}
      {showEditModal && selectedBooking && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white p-6 rounded-lg shadow-lg w-96">
            <h2 className="text-lg font-semibold mb-4">Edit Booking</h2>
            <div className="space-y-4">
              <input
                type="text"
                placeholder="Guest Name"
                value={newBooking.guestName}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, guestName: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <input
                type="text"
                placeholder="Room Type"
                value={newBooking.roomType}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, roomType: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <input
                type="text"
                placeholder="Request (optional)"
                value={newBooking.request}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, request: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <input
                type="text"
                placeholder="Nights (e.g., 3 nights)"
                value={newBooking.nights}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, nights: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <input
                type="text"
                placeholder="Date (e.g., July 10 - 13, 2023)"
                value={newBooking.date}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, date: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <select
                value={newBooking.status}
                onChange={(e) =>
                  setNewBooking({ ...newBooking, status: e.target.value })
                }
                className="w-full p-2 border rounded"
              >
                <option value="Pending">Pending</option>
                <option value="Confirmed">Confirmed</option>
                <option value="Cancelled">Cancelled</option>
              </select>
            </div>
            <div className="flex gap-4 mt-4">
              <button
                onClick={handleSaveEdit}
                className="px-4 py-2 bg-blue-500 text-white rounded"
              >
                Save
              </button>
              <button
                onClick={() => setShowEditModal(false)}
                className="px-4 py-2 border rounded"
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default CustomerList;
