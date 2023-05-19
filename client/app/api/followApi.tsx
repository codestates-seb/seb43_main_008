import axios from "axios";
axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

export const GetFollower = async () => {
  try {
    const response = await axios.get(`/followers`);
    axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data.data;
  } catch (error) {
    throw error;
  }
}

export const GetFollow = async () => {
  try {
    const response = await axios.get(`/follow/followings`);
    axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data.data;
  } catch (error) {
    throw error;
  }
}