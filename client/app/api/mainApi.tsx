import axios from "axios";
axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;

// 📌 Main api

export const GetMain = async (pageQuery: number) => {
  try {
    const response = await axios.get(`/series?page=${pageQuery}`);
    return response.data.data;
  } catch (error) {
    throw error;
  }
}

/*

export const GetMainFilter = async (filter: string) => {
  try {
    // 서버에 새로운 데이터를 요청하고 필터링된 데이터를 가져온다
    const response = await axios.get(`/series?tab=${filter}`);
    return response.data.data;
  } catch (error) {
    console.error('Error fetching data:', error);
    throw error;
  }
};

export const GetMainPage = async (page: number) => {
  try {
    // 서버에 새로운 데이터를 요청하고 필터링된 데이터를 가져온다
    const response = await axios.get(`/series?page=${page}`);
    return response.data.data;
  } catch (error) {
    console.error('Error fetching data:', error);
    throw error;
  }
};

*/

