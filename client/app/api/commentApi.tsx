import axios from "axios";
axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

export const GetComment = async (seriesID: string) => {
  try {
    const response = await axios.get(`comments/${seriesID}`, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjE4LCJzdWIiOiJ0dHR0dGV0NUBuYXZlci5jb20iLCJpYXQiOjE2ODQ2NTA4NTksImV4cCI6MTY4NDY1MjY1OX0.az-mZlcjwZ6MNV2UsQCQYa4BIOebj3nthg19_VszvLozt_V6M1YYZ-8oRVCA9j-a",
      },
    });
    return response.data.data.pagedata;
  } catch (error) {
    throw error;
  }
}

export const PostComment = async (seriesID: string, comment: string) => {
  try {
    const response = await axios.post(`comments/${seriesID}`, { "comment": comment }, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjE4LCJzdWIiOiJ0dHR0dGV0NUBuYXZlci5jb20iLCJpYXQiOjE2ODQ2NTA4NTksImV4cCI6MTY4NDY1MjY1OX0.az-mZlcjwZ6MNV2UsQCQYa4BIOebj3nthg19_VszvLozt_V6M1YYZ-8oRVCA9j-a",
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
}

export const PatchComment = async (seriesID: string, commentId: string, comment: string) => {
  try {
    const response = await axios.patch(`comments/${seriesID}/${commentId}`, { "comment": comment }, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjE4LCJzdWIiOiJ0dHR0dGV0NUBuYXZlci5jb20iLCJpYXQiOjE2ODQ2NTA4NTksImV4cCI6MTY4NDY1MjY1OX0.az-mZlcjwZ6MNV2UsQCQYa4BIOebj3nthg19_VszvLozt_V6M1YYZ-8oRVCA9j-a",
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
}

export const DeleteComment = async (seriesID: string, commentId: string) => {
  try {
    const response = await axios.delete(`comments/${seriesID}/${commentId}`, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjE4LCJzdWIiOiJ0dHR0dGV0NUBuYXZlci5jb20iLCJpYXQiOjE2ODQ2NTA4NTksImV4cCI6MTY4NDY1MjY1OX0.az-mZlcjwZ6MNV2UsQCQYa4BIOebj3nthg19_VszvLozt_V6M1YYZ-8oRVCA9j-a",
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
}

