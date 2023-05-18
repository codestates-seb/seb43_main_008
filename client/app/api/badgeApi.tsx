import axios from "axios";
axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

export const Badge = async (seriesId: string, page: number) => {
  try {
    const response = await axios.get("/badge", {
      headers: {
        "Content-Type": "application/json",
        Authorization: "쑨 토큰",
      },
    });
    return response.data.data;
  } catch (error) {
    throw error;
  }
};
