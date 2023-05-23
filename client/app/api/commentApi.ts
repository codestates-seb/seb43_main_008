import axiosInstance from '../axiosInstance';

export const GetComment = async (seriesID: string) => {
  try {
    const response = await axiosInstance.get(`comments/${seriesID}`);
    console.log(response.data)
    return response.data.data.pagedata;
  } catch (error) {
    throw error;
  }
}

export const PostComment = async (seriesID: string, comment: string) => {
  try {
    const response = await axiosInstance.post(`comments/${seriesID}`, { "comment": comment });
    return response.data;
  } catch (error) {
    throw error;
  }
}

export const PatchComment = async (seriesID: string, commentId: string, comment: string) => {
  try {
    const response = await axiosInstance.patch(`comments/${seriesID}/${commentId}`, { "comment": comment });
    return response.data;
  } catch (error) {
    throw error;
  }
}

export const DeleteComment = async (seriesID: string, commentId: string) => {
  try {
    const response = await axiosInstance.delete(`comments/${seriesID}/${commentId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}

