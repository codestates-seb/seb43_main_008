import axios from "axios";
axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

// 📌 My-page api
export const GetFeed = async () => {
  try {
    const response = await axios.get(`/series/members`, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjQsInN1YiI6ImhnZDEyMzQ1NUBuYXZlci5jb20iLCJpYXQiOjE2ODQzMDI4ODYsImV4cCI6MTY4NDMwNDY4Nn0.cB7tcI3PBd-D-penr6kwd1Lp1xCQjh2icynGevuoTU4xwroeU0NKNH8BRu33PUjP",
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
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjQsInN1YiI6ImhnZDEyMzQ1NUBuYXZlci5jb20iLCJpYXQiOjE2ODQzMDI4ODYsImV4cCI6MTY4NDMwNDY4Nn0.cB7tcI3PBd-D-penr6kwd1Lp1xCQjh2icynGevuoTU4xwroeU0NKNH8BRu33PUjP",
      },
    });
    sessionStorage.setItem("header", response.data.nickName)
    // axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data;
  } catch (error) {
    throw error;
  }
}