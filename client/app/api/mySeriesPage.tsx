import axios from "axios";

axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

export const mySeriesPage = async (seriesId: string) => {
  try {
    const response = await axios.get(`/series/${seriesId}/daylog`, {
      headers: {
        "Content-Type": "application/json",
        Authorization:
          "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjgsInN1YiI6ImFzZGxramZoYUBuYXZlci5jb20iLCJpYXQiOjE2ODQzMjE5MTIsImV4cCI6MTY4NDMyMzcxMn0.Nd3SvHcniGZYIqd_7ld2Z6EP9FqNJ1WkYDk_ry4XArDR_rvhcDx1AU4fH4SYut-p",
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};
