import axios from "axios";

axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

export const mySeriesPage = async () => {
  try {
    const response = await axios.get(`/series/27/daylog`, {
      headers: {
        "Content-Type": "application/json",
        Authorization:
          "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjksInN1YiI6ImFsa2pmaGFAbmF2ZXIuY29tIiwiaWF0IjoxNjg0MzkxMzc3LCJleHAiOjE2ODQzOTMxNzd9.tRXhLjWvqLWyM1HNy-Dheh_toRys3SSS_UEPLBYx__ZPFXIa2gVQzMvOlc1S5w9u",
      },
    });
    console.log(response.data.data);
    return response.data.data;
  } catch (error) {
    throw error;
  }
};
