export const createPayment = async (paymentData) => {
  const token = localStorage.getItem("accessToken");

  try {
    const response = await fetch(
      `http://localhost:8080/payment/api/hoa-don/tao`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(paymentData),
      }
    );

    if (!response.ok) {
      throw new Error("Tạo hóa đơn thất bại");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Lỗi tạo hóa đơn:", error);
    throw error;
  }
};

export const updatePayment = async (paymentId, paymentData) => {
  const token = localStorage.getItem("accessToken");

  try {
    const response = await fetch(
      `http://localhost:8080/payment/api/hoa-don/cap-nhat/${paymentId}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(paymentData),
      }
    );

    if (!response.ok) {
      throw new Error("Cập nhật hóa đơn thất bại");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Lỗi cập nhật hóa đơn:", error);
    throw error;
  }
};

export const getAllPayments = async () => {
  try {
    const response = await fetch(`http://localhost:8080/payment/api/hoa-don`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error("Lấy danh sách hóa đơn thất bại");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Lỗi lấy danh sách hóa đơn:", error);
    throw error;
  }
};
