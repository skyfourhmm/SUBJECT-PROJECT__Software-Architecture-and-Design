export const getListTypeRoom = async () => {
  try {
    const response = await fetch(
      `http://localhost:8080/hotel/api/loai-phong/tat-ca`,
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

export const getRoomsByTypeId = async (typeId) => {
  try {
    const response = await fetch(
      `http://localhost:8080/hotel/api/phong/loai/${typeId}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (!response.ok) {
      throw new Error("Lấy thông tin phòng thất bại");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Lỗi lấy thông tin phòng:", error);
    throw error;
  }
};

export const getRoomById = async (roomId) => {
  try {
    const response = await fetch(
      `http://localhost:8080/hotel/api/phong/by-id?id=${roomId}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (!response.ok) {
      throw new Error("Lấy thông tin phòng thất bại");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Lỗi lấy thông tin phòng:", error);
    throw error;
  }
};
