import axiosInstance from "../axiosInstance";

export async function GetVoteResult(seriesId: string) {
  try {
    const response = await axiosInstance.get(
      `/series/votes/graduation/${seriesId}`
    );
    return response.data;
  } catch (error) {
    throw error;
  }
}
