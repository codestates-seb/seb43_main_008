import axios from "axios";

const Authorization =
  typeof window !== "undefined" ? localStorage.getItem("Authorization") : null;
const RefreshToken =
  typeof window !== "undefined" ? localStorage.getItem("RefreshToken") : null;
const axiosInstance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_URL,
  headers: {
    "Content-Type": "application/json",
    Authorization: Authorization,
  },
});

axiosInstance.interceptors.request.use(
  async (config) => {
    try {
      const response = await axios.get(
        `${process.env.NEXT_PUBLIC_API_URL}/token/check`,
        {
          headers: { Authorization: Authorization },
        }
      );

      if (response.data.code === 410) {
        try {
          const refreshTokenResponse = await axios.get(
            `${process.env.NEXT_PUBLIC_API_URL}/token/reissue`,
            {
              headers: { Authorization: RefreshToken },
            }
          );
          if (refreshTokenResponse.data.code === 410) {
            localStorage.removeItem("Authorization");
            localStorage.removeItem("RefreshToken");
            window.location.href = "/login";
          } else {
            const loginToken = refreshTokenResponse.headers["authorization"];
            localStorage.setItem("Authorization", loginToken);
          }
        } catch (error) {
          throw error;
        }
      }
      return config;
    } catch (error) {
      throw error;
    }
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default axiosInstance;
