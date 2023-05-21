import axios from "axios";
axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;
const Authorization = localStorage.getItem("Authorization");

// 📌 My-page api
export const GetFeed = async () => {
  try {
    const response = await axios.get(`/series/members`, {
      headers: {
        "Content-Type": "application/json",
        Authorization: Authorization,
      },
    });
    // axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data.data;
  } catch (error) {
    throw error;
  }
};

export const GetProfile = async () => {
  try {
    const response = await axios.get(`/feed`, {
      headers: {
        "Content-Type": "application/json",
        Authorization: Authorization,
      },
    });
    sessionStorage.setItem("header", response.data.nickName);
    // axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data.data;
  } catch (error) {
    throw error;
  }
};
