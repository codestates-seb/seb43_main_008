import axios from 'axios';

const Authorization = localStorage.getItem("Authorization");

const axiosInstance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_URL,
  headers: {
    "Content-Type": "application/json",
    'Authorization': Authorization,
  },
});

export default axiosInstance;