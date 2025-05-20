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
