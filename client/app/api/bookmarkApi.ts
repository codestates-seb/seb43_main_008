import axiosInstance from '../axiosInstance';

export const GetPageBookmark = async (pageQuery: number) => {
  try {
    const response = await axiosInstance.get(`/bookmark?page=${pageQuery}`);
    return response.data.data
  } catch (error) {
    throw error;
  }
}

export const GetSeriesBookmark = async (seriesId: string) => {
  try {
    const response = await axiosInstance.get(`/series/${seriesId}`);
    return response.data.data
  } catch (error) {
    throw error;
  }
}

export const PostBookmark = async (seriesId: string) => {
  try {
    const response = await axiosInstance.post(`/bookmark/${seriesId}`);
    return response.data
  } catch (error) {
    throw error;
  }
}

export const DeleteBookmark = async (seriesId: string) => {
  try {
    const response = await axiosInstance.delete(`/bookmark/${seriesId}`);
    return response.data
  } catch (error) {
    throw error;
  }
}
