import axios from "axios";
axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

// 📌 My-page api
export const GetFeed = async () => {
  try {
    const response = await axios.get(`/series/members`, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjQsInN1YiI6ImhnZDEyMzQ1NUBuYXZlci5jb20iLCJpYXQiOjE2ODQyNDAyNDYsImV4cCI6MTY4NDI0MjA0Nn0.NIdgXn7rLqV_d2xsuukoXn0DCXUMpept7k8UJ1rJiY2rzxvEM_FIjAABDO5mnM7-",
      },
    });
    // axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data.data;
  } catch (error) {
    throw error;
  }
}

export const GetProfile = async () => {
  try {
    const response = await axios.get(`/feed`, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjQsInN1YiI6ImhnZDEyMzQ1NUBuYXZlci5jb20iLCJpYXQiOjE2ODQyNDAyNDYsImV4cCI6MTY4NDI0MjA0Nn0.NIdgXn7rLqV_d2xsuukoXn0DCXUMpept7k8UJ1rJiY2rzxvEM_FIjAABDO5mnM7-",
      },
    });
    // axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data;
  } catch (error) {
    throw error;
  }
}