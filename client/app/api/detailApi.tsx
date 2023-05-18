import axios from "axios";

axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

// 📌 Detail api

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


export const GetDaylog = async (seriesId: string, page: number) => {
  try {
    const response = await axios.get(`/series/${seriesId}/daylog?page=${page}`, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjQsInN1YiI6ImhnZDEyMzQ1NUBuYXZlci5jb20iLCJpYXQiOjE2ODQzODc4NTMsImV4cCI6MTY4NDM4OTY1M30.iY7RrdpwkQxxnK1CFmaltTpTf77GcAxS_ZmBL7AuD25pSnXlZ4JWtQRmMqwNXIlw",
      },
    });
    // axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    console.log(response.data.data)
    return response.data.data;
  } catch (error) {
    throw error;
  }
}
