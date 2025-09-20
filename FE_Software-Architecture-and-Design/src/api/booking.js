export const getBooking = async (booking) => {
  const token = localStorage.getItem("accessToken");
  try {
    const response = await fetch(
      `http://localhost:8080/booking/api/phieu-dat-phong`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(booking), // <-- gửi body đây
      }
    );

    if (!response.ok) {
      throw new Error("Lấy thông tin đặt phòng thất bại");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Lỗi lấy thông tin đặt phòng:", error);
    throw error;
  }
};

export const getListBooking = async () => {
  try {
    const response = await fetch(
      `http://localhost:8080/booking/api/phieu-dat-phong`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (!response.ok) {
      throw new Error("Lấy danh sách đặt phòng thất bại");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Lỗi lấy danh sách đặt phòng:", error);
    throw error;
  }
};

export const updateBooking = async (bookingId, bookingData) => {
  console.log("Booking ID:", bookingId, bookingData);
  try {
    const response = await fetch(
      `http://localhost:8080/booking/api/phieu-dat-phong/${bookingId}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(bookingData),
      }
    );

    if (!response.ok) {
      throw new Error("Cập nhật thông tin đặt phòng thất bại");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Lỗi cập nhật thông tin đặt phòng:", error);
    throw error;
  }
};

export const getBookingById = async (bookingId) => {
  try {
    const response = await fetch(
      `http://localhost:8080/booking/api/phieu-dat-phong/${bookingId}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (!response.ok) {
      throw new Error("Lấy thông tin đặt phòng thất bại");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Lỗi lấy thông tin đặt phòng:", error);
    throw error;
  }
};
