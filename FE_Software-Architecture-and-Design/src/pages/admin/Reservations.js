import React, { useEffect, useState, useRef } from "react";
import { useReactToPrint } from "react-to-print";
import ReservationHeader from "../../components/admin/ReservationHeader";
import BookingTable from "../../components/admin/BookingTable";
import {
  getListTypeRoom,
  getRoomById,
  getRoomsByTypeId,
  updateRoom,
} from "../../api/rooms";
import {
  getCustomerById,
  getCustomerByPhone,
  getStaffById,
} from "../../api/user";
import {
  getBooking,
  getBookingById,
  getListBooking,
  updateBooking,
} from "../../api/booking";
import { createPayment, updatePayment } from "../../api/payment";

function Reservations() {
  const [bookings, setBookings] = useState([]);
  const [showPaymentModal, setShowPaymentModal] = useState(false);
  const [paymentInfo, setPaymentInfo] = useState(null);

  useEffect(() => {
    fetchBookingsWithDetails();
  }, []);

  const fetchBookingsWithDetails = async () => {
    try {
      const data = await getListBooking();

      const bookingsWithDetails = await Promise.all(
        data?.map(async (booking) => {
          try {
            // Lấy thông tin khách hàng
            const customerInfo = await getCustomerById(booking?.maKhachHang);

            // Lấy thông tin phòng
            const roomInfo = await getRoomById(booking?.maPhong);

            // Lấy loại phòng nếu tồn tại trong roomInfo
            const roomType = roomInfo?.loaiPhong || null;

            return {
              ...booking,
              khachHang: customerInfo,
              phong: roomInfo,
              loaiPhong: roomType, // Thêm loại phòng
            };
          } catch (error) {
            console.error(
              `Lỗi lấy thông tin cho booking ${booking.maPhieuDat}`,
              error
            );
            return {
              ...booking,
              khachHang: null,
              phong: null,
              loaiPhong: null,
            };
          }
        })
      );

      setBookings(bookingsWithDetails);
    } catch (error) {
      console.error("Error fetching bookings:", error);
    }
  };

  const [searchQuery, setSearchQuery] = useState("");
  const [statusFilter, setStatusFilter] = useState("all");
  const [dateRange, setDateRange] = useState("");
  const [showAddModal, setShowAddModal] = useState(false);
  const [showViewModal, setShowViewModal] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [selectedBooking, setSelectedBooking] = useState(null);
  const [newBooking, setNewBooking] = useState({
    id: "",
    guestName: "",
    roomType: "",
    request: "",
    nights: "",
    date: "",
    status: "Pending",
  });

  const handleAddBooking = () => {
    setNewBooking({
      id: `LG-B${Math.floor(10000 + Math.random() * 90000)}`,
      guestName: "",
      roomType: "",
      request: "",
      nights: "",
      date: "",
      status: "Pending",
    });
    setShowAddModal(true);
  };

  const handleView = (booking) => {
    setSelectedBooking(booking);
    setShowViewModal(true);
  };

  const handleEdit = (booking) => {
    // setSelectedBooking(booking);
    // setNewBooking(booking);
    // setShowEditModal(true);
    console.log("Edit booking:", booking);
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

  const handleConfirmOrCancel = async (booking) => {
    const checkIn = new Date(booking.checkIn);
    const checkOut = new Date(booking.checkOut);
    const timeDiff = checkOut - checkIn;
    const soNgay = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
    const tinhtien = soNgay * booking.loaiPhong.giaPhongTheoDem;
    const now = new Date();
    const thoiGianThanhToan = now.toISOString().slice(0, 19); // YYYY-MM-DDTHH:MM:SS
    const paymentData = {
      trangThai: "Đã Thanh Toán",
      thoiGianThanhToan: thoiGianThanhToan,
      giaTien: tinhtien,
    };

    // cập nhật lại trạng thái đặt phòng
    // const maPhieuDat = {
    //   maKhachHang: booking.khachHang.maKhachHang,
    //   maPhong: booking.maPhong,
    //   maHoaDon: booking.maHoaDon,
    //   checkIn: booking.checkIn,
    //   checkOut: booking.checkOut,
    //   trangThai: "Đã Thanh Toán",
    //   moTa: "Yeu cau phong view bien",
    // };

    // try {
    //   const data = await updateBooking(booking.maPhieuDat, maPhieuDat);
    // } catch (error) {
    //   console.error("Error confirming or canceling booking:", error);
    // }

    try {
      const phieuDat = await getBookingById(booking.maPhieuDat);
      const user = JSON.parse(localStorage.getItem("userInfo"));
      // if (!phieuDat) return;
      // const updatedPayment = await updatePayment(
      //   phieuDat.maHoaDon,
      //   paymentData
      // );
      try {
        // Lấy thông tin khách hàng và nhân viên từ updatedPayment
        const [customer, staff] = await Promise.all([
          getCustomerById(phieuDat.maKhachHang),
          getStaffById(user.maNhanVien),
        ]);
        // Gộp tất cả vào 1 object
        const fullPaymentInfo = {
          ...phieuDat,
          khachHang: customer,
          nhanVien: staff,
          booking: booking,
          giaTien: tinhtien,
        };
        // ✅ Chỉ set 1 lần, sau khi đã có đủ dữ liệu
        setPaymentInfo(fullPaymentInfo);
        setShowPaymentModal(true);
      } catch (error) {
        console.error(
          "❌ Lỗi khi lấy thông tin khách hàng / nhân viên:",
          error
        );
      }
    } catch (error) {
      console.error("❌ Lỗi khi xác nhận hoặc hủy đặt phòng:", error);
    }
  };

  const handleComfirmPayment = async () => {
    // cập nhật lại trạng thái đặt phòng
    const maPhieuDat = {
      maKhachHang: paymentInfo?.maKhachHang,
      maPhong: paymentInfo?.booking?.maPhong,
      maHoaDon: paymentInfo?.maHoaDon,
      checkIn: paymentInfo?.booking.checkIn,
      checkOut: paymentInfo?.booking.checkOut,
      trangThai: "Đã Thanh Toán",
      moTa: "",
    };
    // Cập nhật lại phòng
    await updateRoom(paymentInfo?.booking?.maPhong, {
      ghiChu: "Cập nhật mới",
      tinhTrangPhong: "Trống",
      trangThaiPhong: {
        maTrangThai: "4b9d217d-cf05-4716-8ca6-eeff8bff2731",
      },
    });
    // Cập nhật lại phiếu đặt phòng
    await updateBooking(paymentInfo.maPhieuDat, maPhieuDat);

    // Cập nhật lại hóa đơn
    const now = new Date();
    const thoiGianThanhToan = now.toISOString().slice(0, 19); // YYYY-MM-DDTHH:MM:SS
    const paymentData = {
      trangThai: "Đã Thanh Toán",
      thoiGianThanhToan: thoiGianThanhToan,
      giaTien: paymentInfo?.giaTien,
    };

    await updatePayment(paymentInfo?.maHoaDon, paymentData);

    // Gọi lại API để load lại danh sách
    await fetchBookingsWithDetails();
    setPaymentInfo(null);
    setShowPaymentModal(true);
  };

  // model

  const [phone, setPhone] = useState("");
  const [roomTypes, setRoomTypes] = useState([]);
  const [selectedRoomType, setSelectedRoomType] = useState("");
  const [rooms, setRooms] = useState([]);
  const [selectedRoom, setSelectedRoom] = useState("");
  const [customerInfo, setCustomerInfo] = useState(null);
  const [checkOut, setCheckOut] = useState("");

  useEffect(() => {
    const fetchRoomTypes = async () => {
      const types = await getListTypeRoom();
      setRoomTypes(types);
    };
    fetchRoomTypes();
  }, []);

  useEffect(() => {
    const fetchRooms = async () => {
      if (selectedRoomType) {
        const data = await getRoomsByTypeId(selectedRoomType);
        setRooms(data);
        setSelectedRoom(""); // reset room on type change
      } else {
        setRooms([]);
        setSelectedRoom("");
      }
    };
    fetchRooms();
  }, [selectedRoomType]);

  // Debounce timer
  useEffect(() => {
    if (phone.length < 9) {
      setCustomerInfo(null);
      return;
    }

    const handler = setTimeout(async () => {
      try {
        const customer = await getCustomerByPhone(phone);
        setCustomerInfo(customer);
      } catch (error) {
        setCustomerInfo(null);
      }
    }, 300); // đợi 300ms sau khi ngừng gõ mới gọi API

    return () => {
      clearTimeout(handler);
    };
  }, [phone]);

  const handleSave = async () => {
    const todayLocal = new Date().toLocaleDateString("sv-SE");
    const booking = {
      maKhachHang: customerInfo?.maKhachHang,
      maPhong: selectedRoom,
      maHoaDon: null, // ban đầu chưa có hóa đơn
      checkIn: todayLocal,
      checkOut: checkOut,
      tienCoc: null,
      trangThai: "Đã đặt",
      moTa: "null",
    };

    try {
      // 1. Tạo phiếu đặt phòng
      const data = await getBooking(booking);

      if (data && data.maPhieuDat) {
        // 2. Lấy nhân viên từ localStorage
        const nhanvien = JSON.parse(localStorage.getItem("userInfo"));

        // 3. Gửi request tạo hóa đơn
        const paymentData = await createPayment({
          trangThai: "ChuaThanhToan",
          thoiGianThanhToan: null,
          maPhieuDat: data.maPhieuDat,
          giaTien: 0,
          maKhachHang: data.maKhachHang,
          maNhanVien: nhanvien?.maNhanVien,
        });

        // 4. Cập nhật lại phiếu đặt với mã hóa đơn mới
        if (paymentData?.maHoaDon) {
          await updateBooking(data.maPhieuDat, {
            ...data,
            maHoaDon: paymentData.maHoaDon,
          });
        }
      }

      // 5. Cập nhật trạng thái phòng
      await updateRoom(data?.maPhong, {
        ghiChu: "Cập nhật mới",
        tinhTrangPhong: "Đã đặt",
        trangThaiPhong: {
          maTrangThai: "9c4e81bd-5d2b-401b-b6db-aac49e427dc9",
        },
      });

      // 6. Lấy lại danh sách phòng mới theo loại phòng hiện tại (để cập nhật rooms state)
      if (selectedRoomType) {
        const updatedRooms = await getRoomsByTypeId(selectedRoomType);
        setRooms(updatedRooms);
      }

      // 7. Lấy lại danh sách booking để cập nhật giao diện
      await fetchBookingsWithDetails();

      // 8. Reset form
      setShowAddModal(false);
      setPhone("");
      setSelectedRoomType("");
      setSelectedRoom("");
      setCheckOut("");
    } catch (error) {
      console.error("Error saving booking:", error);
    }
  };

  const handleCancel = () => {
    setShowAddModal(false);
    setPhone("");
    setSelectedRoomType("");
    setRooms([]);
    setSelectedRoom("");
    setCheckOut("");
  };

  const formatDateTime = (isoString) => {
    const date = new Date(isoString);
    return date.toLocaleString("vi-VN", {
      hour12: false,
      hour: "2-digit",
      minute: "2-digit",
      second: "2-digit",
      day: "2-digit",
      month: "2-digit",
      year: "numeric",
    });
  };

  // In thông tin thanh toán
  const componentRef = useRef();
  const handlePrint = useReactToPrint({
    content: () => componentRef.current,
  });

  // handle search
  const [bookingSearch, setBookingSearch] = useState(bookings);
  useEffect(() => {
    if (!searchQuery) {
      setBookingSearch(bookings);
      return;
    }
    const foundBooking = bookings.find(
      (b) => b.khachHang?.soDienThoai === searchQuery
    );
    setBookingSearch(foundBooking ? [foundBooking] : []);
  }, [searchQuery, bookings]);

  return (
    <div className="p-6">
      <ReservationHeader
        searchQuery={searchQuery}
        setSearchQuery={setSearchQuery}
        statusFilter={statusFilter}
        setStatusFilter={setStatusFilter}
        dateRange={dateRange}
        setDateRange={setDateRange}
        onAddBooking={handleAddBooking}
      />
      <BookingTable
        bookings={bookingSearch}
        onView={handleView}
        onEdit={handleEdit}
        onConfirmOrCancel={handleConfirmOrCancel}
      />

      {showAddModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
          <div className="bg-white p-6 rounded-xl shadow-lg w-[500px] space-y-4">
            <h2 className="text-xl font-semibold mb-2">
              Chọn phòng cho khách hàng
            </h2>

            {/* Số điện thoại */}
            <div>
              <label className="block mb-1">Số điện thoại khách hàng</label>
              <input
                type="text"
                className="w-full p-2 border rounded-md"
                value={phone}
                onChange={(e) => setPhone(e.target.value)}
              />
              {/* Hiển thị tên + CCCD khách khi có */}
              {customerInfo && (
                <div className="mt-1 text-sm text-gray-700">
                  <div>Tên: {customerInfo?.hoTen}</div>
                  <div>CCCD: {customerInfo?.cccd}</div>
                </div>
              )}
            </div>

            {/* Loại phòng */}
            <div>
              <label className="block mb-1">Loại phòng</label>
              <select
                className="w-full p-2 border rounded-md"
                value={selectedRoomType}
                onChange={(e) => setSelectedRoomType(e.target.value)}
              >
                <option value="">-- Chọn loại phòng --</option>
                {roomTypes.map((type) => (
                  <option key={type.maLoaiPhong} value={type.maLoaiPhong}>
                    {type.tenLoaiPhong}
                  </option>
                ))}
              </select>
            </div>

            {/* Danh sách phòng */}
            <div>
              <label className="block mb-1">Chọn phòng</label>
              <select
                className="w-full p-2 border rounded-md"
                value={selectedRoom}
                onChange={(e) => setSelectedRoom(e.target.value)}
                disabled={!rooms.length}
              >
                <option value="">-- Chọn phòng --</option>
                {rooms
                  .filter((room) => room.tinhTrangPhong === "Trống") // lọc chỉ phòng Trống
                  .map((room) => (
                    <option key={room.maPhong} value={room.maPhong}>
                      {room.tenPhong} ({room.tinhTrangPhong})
                    </option>
                  ))}
              </select>
            </div>

            {/* Ngày Check-Out */}
            <div>
              <label className="block mb-1">Ngày Check-Out</label>
              <input
                type="date"
                className="w-full p-2 border rounded-md"
                value={checkOut}
                min={new Date().toISOString().split("T")[0]} // chỉ cho chọn từ hôm nay trở đi
                onChange={(e) => setCheckOut(e.target.value)}
              />
            </div>

            {/* Buttons */}
            <div className="flex justify-end space-x-3 mt-4">
              <button
                onClick={handleCancel}
                className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
              >
                Cancel
              </button>
              <button
                onClick={handleSave}
                className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 disabled:opacity-50"
                disabled={
                  !phone ||
                  !customerInfo ||
                  !selectedRoomType ||
                  !selectedRoom ||
                  !checkOut
                }
              >
                Save
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Modal for View Booking */}
      {showViewModal && selectedBooking && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white p-6 rounded-lg shadow-lg w-96">
            <h2 className="text-lg font-semibold mb-4">Booking Details</h2>
            <div className="space-y-2">
              <p>
                <strong>ID:</strong> {selectedBooking.id}
              </p>
              <p>
                <strong>Guest Name:</strong> {selectedBooking.guestName}
              </p>
              <p>
                <strong>Room Type:</strong> {selectedBooking.roomType}
              </p>
              <p>
                <strong>Request:</strong> {selectedBooking.request || "None"}
              </p>
              <p>
                <strong>Duration:</strong> {selectedBooking.nights}
              </p>
              <p>
                <strong>Check-In/Out:</strong> {selectedBooking.date}
              </p>
              <p>
                <strong>Status:</strong> {selectedBooking.status}
              </p>
            </div>
            <div className="flex justify-end mt-4">
              <button
                onClick={() => setShowViewModal(false)}
                className="px-4 py-2 border rounded"
              >
                Close
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

      {showPaymentModal && paymentInfo && (
        <div
          ref={componentRef}
          className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50"
        >
          <div className="bg-white rounded-2xl shadow-xl w-full max-w-2xl p-6 relative">
            {/* Nút đóng */}
            <button
              onClick={() => setShowPaymentModal(false)}
              className="absolute top-3 right-3 text-gray-500 hover:text-gray-700 text-xl"
            >
              ✕
            </button>

            <h2 className="text-2xl font-bold mb-6 text-center">
              Thông tin thanh toán
            </h2>

            <div className="grid grid-cols-2 gap-4 text-base">
              <p>
                <span className="font-semibold">Trạng thái:</span>{" "}
                {paymentInfo.trangThai}
              </p>
              <p>
                <span className="font-semibold">Giá tiền:</span>{" "}
                {paymentInfo?.giaTien?.toLocaleString()} VND
              </p>

              <p>
                <span className="font-semibold">Thời gian tạo phiếu:</span>{" "}
                {formatDateTime(paymentInfo.thoiGianTao)}
              </p>

              <p>
                <span className="font-semibold">Khách hàng:</span>{" "}
                {paymentInfo.khachHang?.hoTen}
              </p>
              <p>
                <span className="font-semibold">Nhân viên thanh toán:</span>{" "}
                {paymentInfo.nhanVien?.hoTen}
              </p>

              <p>
                <span className="font-semibold">Số phòng:</span>{" "}
                {paymentInfo.booking?.phong?.tenPhong}
              </p>
              <p>
                <span className="font-semibold">Loại phòng:</span>{" "}
                {paymentInfo?.booking?.phong?.loaiPhong?.tenLoaiPhong}
              </p>

              <p>
                <span className="font-semibold">Check-in:</span>{" "}
                {paymentInfo.booking.checkIn}
              </p>
              <p>
                <span className="font-semibold">Check-out:</span>{" "}
                {paymentInfo.booking.checkOut}
              </p>

              <p>
                <span className="font-semibold">Người tạo phiếu:</span>{" "}
                {paymentInfo.nhanVien?.hoTen}
              </p>
            </div>

            <div className="mt-6 flex justify-end gap-3">
              <button
                onClick={handleComfirmPayment}
                className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
              >
                Xác nhận thanh toán
              </button>

              <button
                onClick={() => setShowPaymentModal(false)}
                className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
              >
                Đóng
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default Reservations;
