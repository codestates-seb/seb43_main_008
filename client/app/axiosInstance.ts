import axios from "axios";

const Authorization =
  typeof window !== "undefined" ? localStorage.getItem("Authorization") : null;

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
      // 여기서 token의 유효성을 검사합니다.
      const response = await axios.get(
        `${process.env.NEXT_PUBLIC_API_URL}/token/check`,
        {
          headers: { Authorization: Authorization },
        }
      );
      console.log(response);

      return config;
    } catch (error) {
      // 여기서 에러 처리를 합니다.
      console.error(error);
      throw error;
    }
  },
  (error) => {
    // 요청에 대한 에러를 처리합니다.
    return Promise.reject(error);
  }
);

export default axiosInstance;
