import axios from "axios";

axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;


export const PostBookmark = async (seriesId: string) => {
  try {
    const response = await axios.get(`/bookmark/${seriesId}`, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjQsInN1YiI6ImhnZDEyMzQ1NUBuYXZlci5jb20iLCJpYXQiOjE2ODQ0MTEwMTgsImV4cCI6MTY4NDQxMjgxOH0.pFXjA8I8yluMRGTzzsWtlzpDuwFsFjCGAqmJ0lNzoS_v2SRFjMvHqwk0V6I-9zrv",
      },
    });
    return response.data
  } catch (error) {
    throw error;
  }
}
