import axios from "axios";
axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

export const GetComment = async (seriesID: string) => {
  try {
    const response = await axios.get(`comments/${seriesID}`, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjE3LCJzdWIiOiJ0dGV0NUBuYXZlci5jb20iLCJpYXQiOjE2ODQ0MTMxNTQsImV4cCI6MTY4NDQxNDk1NH0.g4818krXAKEhU53KFYvoRMwU_IyAWGGWYoP0u_YJPZoFuDMOt2fOQguyajCjemqe",
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
}

export const PostComment = async (seriesID: string, comment: string) => {
  try {
    const response = await axios.post(`comments/${seriesID}`, { comment }, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjE3LCJzdWIiOiJ0dGV0NUBuYXZlci5jb20iLCJpYXQiOjE2ODQ0MTMxNTQsImV4cCI6MTY4NDQxNDk1NH0.g4818krXAKEhU53KFYvoRMwU_IyAWGGWYoP0u_YJPZoFuDMOt2fOQguyajCjemqe",
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
}

export const PatchComment = async (seriesID: string, commentId: string, comment: string) => {
  try {
    const response = await axios.patch(`comments/${seriesID}/${commentId}`, { comment }, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjE3LCJzdWIiOiJ0dGV0NUBuYXZlci5jb20iLCJpYXQiOjE2ODQ0MTMxNTQsImV4cCI6MTY4NDQxNDk1NH0.g4818krXAKEhU53KFYvoRMwU_IyAWGGWYoP0u_YJPZoFuDMOt2fOQguyajCjemqe",
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
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjE3LCJzdWIiOiJ0dGV0NUBuYXZlci5jb20iLCJpYXQiOjE2ODQ0MTMxNTQsImV4cCI6MTY4NDQxNDk1NH0.g4818krXAKEhU53KFYvoRMwU_IyAWGGWYoP0u_YJPZoFuDMOt2fOQguyajCjemqe",
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
}

