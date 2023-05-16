import axios from "axios";
axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

// 📌 My-page api
export const GetMembers = async () => {
  try {
    const response = await axios.get(`/series/members`, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjQsInN1YiI6ImhnZDEyMzQ1NUBuYXZlci5jb20iLCJpYXQiOjE2ODQyMjM2OTQsImV4cCI6MTY4NDIyNTQ5NH0.nngfIgww83F0Cg2j7PCuOYGrWtwPLIOPkXWQyu_nMr8GpKhQjOxwscFqsGBZWPes",
      },
    });
    // axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data.data;
  } catch (error) {
    throw error;
  }
}
