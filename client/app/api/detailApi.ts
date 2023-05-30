import axiosInstance from '../axiosInstance';

// 📌 Detail api

export const GetDaylog = async (seriesId: string, page: number) => {
  try {
    const response = await axiosInstance.get(`/series/${seriesId}/daylog?page=${page}`);
    console.log(response.data.data.pagedata)
    return response.data.data.pagedata;
  } catch (error) {
    throw error;
  }
}

