// import axios from "axios";
import axiosInstance from '../axiosInstance';
// import { forDefaultAxios } from "./axios"
// forDefaultAxios()

// 📌 Detail api


export const GetDaylog = async (seriesId: string, page: number) => {
  try {
    const response = await axiosInstance.get(`/series/${seriesId}/daylog?page=${page}`);
    // axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data.data.data;
  } catch (error) {
    throw error;
  }
}


/*
export const GetSeries = async (seriesId: string) => {
  try {
    const response = await axios.get(`/series/${seriesId}`);
    axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data.data;
  } catch (error) {
    throw error;
  }
}
*/

