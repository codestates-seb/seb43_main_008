import axios, { AxiosError } from "axios";
axios.defaults.baseURL = "http://localhost:8000";

const getList = async () => {
  try {
    const response = await axios.get("/main", {
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.data;
  } catch (e) {
    const err = e as AxiosError<any>;
    alert(err.response?.data.message);
  }
};
