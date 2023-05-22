import axiosInstance from '../axiosInstance';

export const GetProfile = async () => {
  try {
    const response = await axiosInstance.get(`/feed`);
    sessionStorage.setItem("header", response.data.nickName);
    return response.data.data;
  } catch (error) {
    throw error;
  }
};

export const GetFeed = async (nickName: string) => {
  try {
    const response = await axiosInstance.get(`/feed/series/맹구당`);
    return response.data.data.pagedata
  } catch (error) {
    throw error;
  }
};

