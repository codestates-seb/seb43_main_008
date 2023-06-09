import axiosInstance from "../axiosInstance";

export const GetMyProfile = async () => {
  try {
    const response = await axiosInstance.get(`/feed`);
    sessionStorage.setItem("header", response.data.nickName);
    return response.data.data;
  } catch (error) {
    throw error;
  }
};

export const GetMyFeed = async () => {
  try {
    const response = await axiosInstance.get(`/feed/series`);
    return response.data.data.pagedata
  } catch (error) {
    throw error;
  }
};

export const GetProfile = async (nickName: string) => {
  try {
    const response = await axiosInstance.get(`/feed/${nickName}`);
    sessionStorage.setItem("header", response.data.nickName);
    return response.data.data;
  } catch (error) {
    throw error;
  }
};

export const GetFeed = async (nickName: string) => {
  try {
    const response = await axiosInstance.get(`/feed/series/${nickName}`);
    return response.data.data.pagedata
  } catch (error) {
    throw error;
  }
};
