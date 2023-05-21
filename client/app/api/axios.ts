import axios from "axios";


export const forDefaultAxios = () => {
  axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

  if (typeof window !== "undefined") {
    const Authorization = localStorage.getItem("Authorization");
    axios.defaults.headers.common["authorization"] = `${Authorization}`;
  }
}

