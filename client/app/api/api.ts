import axios from "axios";

axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

export const GetMain = async () => {
  try {
    const response = await axios.get("/series");
    return response.data.data;
  } catch (error) {
    throw error;
  }
}

// export const Get