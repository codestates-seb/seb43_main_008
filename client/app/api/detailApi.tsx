import axios from "axios";

axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

// 📌 Detail api
export const GetSeries = async (seriesId: string) => {
  try {
    const response = await axios.get(`/series/${seriesId}`);
    axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data.data;
  } catch (error) {
    throw error;
  }
}

export const GetDaylog = async (seriesId: string, page: number) => {
  try {
    const response = await axios.get(`/series/${seriesId}/daylog?page=${page}`, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjQsInN1YiI6ImhnZDEyMzQ1NUBuYXZlci5jb20iLCJpYXQiOjE2ODQzMjc3NjgsImV4cCI6MTY4NDMyOTU2OH0.fIu7bJDVor_7xolpQSwjkaWEhX2CN_dxVnML5fTmCloSLrs8_jNGuvFnPh6rlwxc",
      },
    });
    // axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data.data;
  } catch (error) {
    throw error;
  }
}
