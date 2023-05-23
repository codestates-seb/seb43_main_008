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

export const PostFollowing = async (nickName: string) => {
  try {
    const response = await axiosInstance.post(`/follow/${nickName}`);
    return response.data
  } catch (error) {
    throw error;
  }
}

export const DeleteUnFollowing = async (nickName: string) => {
  try {
    const response = await axiosInstance.delete(`/unfollow/${nickName}`);
    return response.data
  } catch (error) {
    throw error;
  }
}