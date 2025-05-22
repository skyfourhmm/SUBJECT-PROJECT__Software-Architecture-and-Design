import React from "react";

function CustomerTable({ customerList, onEdit }) {
  return (
    <div className="bg-white rounded-lg shadow">
      <table className="w-full">
        <thead className="bg-gray-50">
          <tr>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Name
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Gender
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Date of Birth
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Address
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Phone
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              diemThuong
            </th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
              Action
            </th>
          </tr>
        </thead>
        <tbody className="divide-y divide-gray-200">
          {customerList.map((customer) => (
            <tr key={customer.id} className="hover:bg-gray-50">
              <td className="px-6 py-4">
                <div>
                  <div className="font-medium">{customer.hoTen}</div>
                  <div className="text-sm text-gray-500">
                    {customer.maKhachHang}
                  </div>
                </div>
              </td>
              <td className="px-6 py-4">{customer.gioiTinh}</td>
              <td className="px-6 py-4">{customer.ngaySinh}</td>
              <td className="px-6 py-4">{customer.diaChi}</td>
              <td className="px-6 py-4">{customer.soDienThoai}</td>
              <td className="px-6 py-4">{customer.diemThuong}</td>
              <td className="px-6 py-4">
                <div className="flex gap-2">
                  <button
                    onClick={() => onEdit(customer)}
                    className="p-1 hover:bg-gray-100 rounded"
                  >
                    📝
                  </button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default CustomerTable;
