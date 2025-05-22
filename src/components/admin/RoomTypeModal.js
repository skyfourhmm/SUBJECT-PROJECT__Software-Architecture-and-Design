// File: src/components/admin/RoomTypeModal.jsx
import React from "react";

function RoomTypeModal({ isOpen, onClose, roomType, roomList }) {
  if (!isOpen || !roomType) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-40 flex justify-center items-center z-50">
      <div className="bg-white rounded-2xl p-6 w-[700px] max-h-[90vh] overflow-y-auto shadow-lg">
        <h2 className="text-xl font-bold mb-4">Thông tin loại phòng</h2>

        <div className="space-y-2 mb-4">
          <img
            src={roomType.hinhAnh}
            alt={roomType.tenLoaiPhong}
            className="rounded-lg w-full h-60 object-cover"
          />
          <div>
            <strong>Tên:</strong> {roomType.tenLoaiPhong}
          </div>
          <div>
            <strong>Diện tích:</strong> {roomType.dienTich} m²
          </div>
          <div>
            <strong>Đối tượng sử dụng:</strong> {roomType.doiTuongSuDung}
          </div>
          <div>
            <strong>Tiện nghi:</strong> {roomType.tienNghi}
          </div>
          <div>
            <strong>Giá theo đêm:</strong>{" "}
            {roomType.giaPhongTheoDem.toLocaleString()} VNĐ
          </div>
          <div>
            <strong>Giá theo giờ:</strong>{" "}
            {roomType.giaPhongTheoGio.toLocaleString()} VNĐ
          </div>
        </div>

        <hr className="my-4" />

        <h3 className="text-lg font-semibold mb-2">Danh sách phòng</h3>
        <div className="grid grid-cols-2 gap-4">
          {roomList.map((room) => (
            <div
              key={room.maPhong}
              className="p-3 border rounded-xl shadow-sm bg-gray-50"
            >
              <div>
                <strong>Tên phòng:</strong> {room.tenPhong}
              </div>
              <div>
                <strong>Tình trạng:</strong> {room.tinhTrangPhong}
              </div>
              <div>
                <strong>Ghi chú:</strong> {room.ghiChu || "Không có"}
              </div>
              <div>
                <strong>Trạng thái:</strong> {room.trangThaiPhong?.tenTrangThai}
              </div>
            </div>
          ))}
        </div>

        <div className="mt-6 text-right">
          <button
            onClick={onClose}
            className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
          >
            Đóng
          </button>
        </div>
      </div>
    </div>
  );
}

export default RoomTypeModal;
