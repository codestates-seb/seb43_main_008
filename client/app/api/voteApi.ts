import axiosInstance from '../axiosInstance';

export const GetVote = async (seriesId: string) => {
  try {
    const response = await axiosInstance.get(`/series/votes/${seriesId}`);
    return response.data.data
  } catch (error) {
    throw error;
  }
}

export const PutVote = async (seriesId: string, voting: string) => {
  try {
    const response = await axiosInstance.put(`/series/votes/${seriesId}/${voting}`);
    console.log(response.data)
    return response.data.message
  } catch (error) {
    throw error;
  }
}

