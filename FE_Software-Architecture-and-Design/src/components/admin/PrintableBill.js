import React, { forwardRef } from "react";

const PrintableBill = forwardRef(({ billInfo }, ref) => {
  if (!billInfo) return null;

  return (
    <div ref={ref} className="p-6 text-black bg-white w-[800px]">
      <h2 className="text-2xl font-bold text-center mb-4">
        HÓA ĐƠN THANH TOÁN
      </h2>
      <p>
        <strong>Khách hàng:</strong> {billInfo.khachHang?.hoTen}
      </p>
      <p>
        <strong>Trạng thái:</strong> {billInfo.trangThai}
      </p>
      <p>
        <strong>Giá tiền:</strong> {billInfo.giaTien.toLocaleString()} VND
      </p>
      <p>
        <strong>Ngày tạo:</strong> {billInfo.thoiGianTao}
      </p>
      <p>
        <strong>Nhân viên:</strong> {billInfo.nhanVien?.hoTen}
      </p>
      {/* Các thông tin khác nếu cần */}
    </div>
  );
});

export default PrintableBill;
