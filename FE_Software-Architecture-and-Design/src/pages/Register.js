import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { register } from "../api/user";

function Register() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    tenDangNhap: "",
    matKhau: "",
    loaiNguoiDung: "nhanVien",
    xacNhanMatKhau: "",
    hoTen: "",
    gioiTinh: "Nam",
    ngaySinh: "",
    diaChi: "",
    soDienThoai: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleRegister = async (e) => {
    e.preventDefault();

    if (form.matKhau !== form.xacNhanMatKhau) {
      alert("Mật khẩu xác nhận không khớp!");
      return;
    }

    const { xacNhanMatKhau, ...payload } = form;

    try {
      await register(payload);
      alert("Đăng ký thành công!");
      navigate("/login");
    } catch (err) {
      console.error("Đăng ký thất bại:", err);
      alert("Có lỗi xảy ra khi đăng ký.");
    }
  };

  return (
    <div className="flex h-screen">
      <div className="w-1/2 flex flex-col justify-center px-16 bg-gray-100">
        <h2 className="text-3xl font-bold mb-6">Tạo Tài Khoản Mới</h2>

        <form onSubmit={handleRegister}>
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block mb-1 text-gray-700">Tên Đăng Nhập</label>
              <input
                type="text"
                name="tenDangNhap"
                value={form.tenDangNhap}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-full mb-2"
              />
            </div>

            <div>
              <label className="block mb-1 text-gray-700">Mật Khẩu</label>
              <input
                type="password"
                name="matKhau"
                value={form.matKhau}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-full mb-2"
              />
            </div>

            <div>
              <label className="block mb-1 text-gray-700">
                Xác Nhận Mật Khẩu
              </label>
              <input
                type="password"
                name="xacNhanMatKhau"
                value={form.xacNhanMatKhau}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-full mb-2"
              />
            </div>

            <div>
              <label className="block mb-1 text-gray-700">Họ Tên</label>
              <input
                type="text"
                name="hoTen"
                value={form.hoTen}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-full mb-2"
              />
            </div>

            <div>
              <label className="block mb-1 text-gray-700">Giới Tính</label>
              <select
                name="gioiTinh"
                value={form.gioiTinh}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-full mb-2"
              >
                <option value="Nam">Nam</option>
                <option value="Nữ">Nữ</option>
              </select>
            </div>

            <div>
              <label className="block mb-1 text-gray-700">Ngày Sinh</label>
              <input
                type="date"
                name="ngaySinh"
                value={form.ngaySinh}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-full mb-2"
              />
            </div>

            <div>
              <label className="block mb-1 text-gray-700">Địa Chỉ</label>
              <input
                type="text"
                name="diaChi"
                value={form.diaChi}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-full mb-2"
              />
            </div>

            <div>
              <label className="block mb-1 text-gray-700">Số Điện Thoại</label>
              <input
                type="text"
                name="soDienThoai"
                value={form.soDienThoai}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-full mb-2"
              />
            </div>
          </div>

          <button
            type="submit"
            className="w-full bg-green-600 text-white py-2 rounded-full hover:bg-green-700 mt-4"
          >
            Đăng Ký
          </button>
        </form>

        <p className="mt-6 text-center text-gray-600">
          Đã có tài khoản?{" "}
          <a href="/login" className="text-blue-500">
            Đăng nhập
          </a>
        </p>
      </div>

      {/* Right section giữ nguyên */}
      <div className="w-1/2 bg-white flex flex-col justify-center items-center text-center p-12">
        <div className="grid grid-cols-2 gap-4">
          {/* Copy ảnh từ Login nếu cần */}
        </div>
        <h2 className="text-2xl font-bold mt-6">
          Tìm kiếm kỳ nghỉ hoàn hảo cho bạn
        </h2>
        <p className="text-gray-600 mt-4">
          Đăng ký để bắt đầu trải nghiệm dịch vụ đặt phòng nhanh chóng và đáng
          tin cậy của chúng tôi.
        </p>
      </div>
    </div>
  );
}

export default Register;
