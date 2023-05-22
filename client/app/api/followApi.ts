import axiosInstance from '../axiosInstance';


export const GetFollower = async () => {
  try {
    const response = await axiosInstance.get(`/follow/followers`);
    return response.data.pagedata;
  } catch (error) {
    throw error;
  }
}

export const GetFollowing = async () => {
  try {
    const response = await axiosInstance.get(`/follow/followings`);
    return response.data.pagedata;
  } catch (error) {
    throw error;
  }
}