// login.js
export const login = async (userName, password) => {
  try {
    const response = await fetch(
      "http://localhost:8080/identity/api/auth/login",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username: userName, // dùng biến được truyền vào
          password: password, // dùng biến được truyền vào
        }),
      }
    );

    if (!response.ok) {
      throw new Error("Đăng nhập thất bại");
    }

    const data = await response.json();

    return data;
  } catch (error) {
    console.error("Lỗi login:", error);
    throw error;
  }
};

export const register = async (userData) => {
  try {
    const response = await fetch(
      "http://localhost:8080/identity/api/auth/register",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userData),
      }
    );

    const contentType = response.headers.get("content-type");

    if (!response.ok) {
      if (contentType && contentType.includes("application/json")) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Đăng ký thất bại");
      } else {
        const errorText = await response.text();
        throw new Error(errorText || "Đăng ký thất bại");
      }
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Lỗi register:", error);
    throw error;
  }
};

export const logout = async () => {
  console.log("Logout function called");
  try {
    const response = await fetch(
      "http://localhost:8080/identity/api/auth/logout",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      }
    );

    if (!response.ok) {
      throw new Error("Đăng xuất thất bại");
    }

    localStorage.removeItem("accessToken");
    localStorage.removeItem("userInfo");
  } catch (error) {
    console.error("Lỗi logout:", error);
    throw error;
  }
};

export const getListCustomer = async () => {
  try {
    const response = await fetch(
      "http://localhost:8080/identity/api/customer/list",
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (!response.ok) {
      throw new Error("Lấy danh sách khách hàng thất bại");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Lỗi lấy danh sách khách hàng:", error);
    throw error;
  }
};

export const getCustomerByPhone = async (phone) => {
  try {
    const response = await fetch(
      `http://localhost:8080/identity/api/customer/by-sdt?soDienThoai=${phone}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (!response.ok) {
      throw new Error("Lấy thông tin khách hàng thất bại");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Lỗi lấy thông tin khách hàng:", error);
    throw error;
  }
};

export const getCustomerById = async (id) => {
  try {
    const response = await fetch(
      `http://localhost:8080/identity/api/customer/by-id?id=${id}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (!response.ok) {
      throw new Error("Lấy thông tin khách hàng thất bại");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Lỗi lấy thông tin khách hàng:", error);
    throw error;
  }
};

export const getStaffById = async (id) => {
  try {
    const response = await fetch(
      `http://localhost:8080/identity/api/nhanvien/${id}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (!response.ok) {
      throw new Error("Lấy thông tin nhân viên thất bại");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Lỗi lấy thông tin nhân viên:", error);
    throw error;
  }
};
