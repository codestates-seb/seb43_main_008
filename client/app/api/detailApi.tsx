import axios from "axios";

axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

// 📌 Detail api


export const GetDaylog = async (seriesId: string, page: number) => {
  try {
    const response = await axios.get(`/series/${seriesId}/daylog?page=${page}`, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjE4LCJzdWIiOiJ0dHR0dGV0NUBuYXZlci5jb20iLCJpYXQiOjE2ODQ2NTA4NTksImV4cCI6MTY4NDY1MjY1OX0.az-mZlcjwZ6MNV2UsQCQYa4BIOebj3nthg19_VszvLozt_V6M1YYZ-8oRVCA9j-a",
      },
    });
    // axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data.data.data;
  } catch (error) {
    throw error;
  }
}


/*
export const GetSeries = async (seriesId: string) => {
  try {
    const response = await axios.get(`/series/${seriesId}`);
    axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data.data;
  } catch (error) {
    throw error;
  }
}
*/

