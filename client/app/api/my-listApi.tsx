import axios from "axios";

axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

export const mySeriesPage = async () => {
  try {
    const response = await axios.get(`/series/27/daylog`, {
      headers: {
        "Content-Type": "application/json",
        Authorization:
          "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjI1LCJzdWIiOiJhbGtmaGFAbmF2ZXIuY29tIiwiaWF0IjoxNjg0NTgxNTgyLCJleHAiOjE2ODQ1ODMzODJ9.OBpS8zbizJAPnqReEbLp1baDG3VVgrXD7iQl4n7A6_1ufU2RbZ6j1mdAjParW586",
      },
    });
    console.log(response.data.data);
    return response.data.data;
  } catch (error) {
    throw error;
  }
};
