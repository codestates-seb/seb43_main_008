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

export async function VoteEnd(seriesId: string) {
  try {
    // isQuit = false면, 졸업시키기
    const response = await axiosInstance.patch(
      `/series/votes/graduation/${seriesId}?isQuit=false`
    );

    console.log(response);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function VoteNoEnd(seriesId: string) {
  try {
    // isQuit = true면, 재투표 받기
    const response = await axiosInstance.patch(
      `/series/votes/graduation/${seriesId}?isQuit=true`
    );
    console.log(response);
    return response.data;
  } catch (error) {
    throw error;
  }
}
