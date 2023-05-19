import axios from "axios";
axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

// 📌 My-page api
export const GetFeed = async () => {
  try {
    const response = await axios.get(`/series/members`, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjE3LCJzdWIiOiJ0dGV0NUBuYXZlci5jb20iLCJpYXQiOjE2ODQ0MTMxNTQsImV4cCI6MTY4NDQxNDk1NH0.g4818krXAKEhU53KFYvoRMwU_IyAWGGWYoP0u_YJPZoFuDMOt2fOQguyajCjemqe",
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
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjE3LCJzdWIiOiJ0dGV0NUBuYXZlci5jb20iLCJpYXQiOjE2ODQ0MTMxNTQsImV4cCI6MTY4NDQxNDk1NH0.g4818krXAKEhU53KFYvoRMwU_IyAWGGWYoP0u_YJPZoFuDMOt2fOQguyajCjemqe",
      },
    });
    sessionStorage.setItem("header", response.data.nickName)
    // axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data;
  } catch (error) {
    throw error;
  }
}