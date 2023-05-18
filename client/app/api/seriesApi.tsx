import axios from "axios";
axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

export const Series = async (seriesId: string, page: number) => {
  try {
    const response = await axios.post("/series", {
      headers: {
        "Content-Type": "application/json",
        Authorization: "쑨 토큰",
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};
