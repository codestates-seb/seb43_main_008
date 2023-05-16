import axios from "axios";
// import { useSWRConfig } from 'swr'


axios.defaults.baseURL = process.env.NEXT_PUBLIC_API_URL;


export const GetMain = async () => {
  try {
    const response = await axios.get(`/series`);
    return response.data.data;
  } catch (error) {
    throw error;
  }
}

// 📌 Main api
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

// 📌 Detail api
export const GetSeries = async (seriesId: string) => {
  try {
    const response = await axios.get(`/series/${seriesId}`);
    axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data.data;
  } catch (error) {
    throw error;
  }
}

// 📌 Member api
export const GetMembers = async () => {
  try {
    const response = await axios.get(`/series/members`);
    axios.defaults.headers.common["Authorization"] = `Bearer ${response.headers.authorization}`;
    return response.data.data;
  } catch (error) {
    throw error;
  }
}

/* 

export const GetMain = async () => {
  const { mutate } = useSWRConfig();

  try {
    const response = await axios.get(`/series`);
    const newData = response.data;

    mutate('/series', newData, false)

    return newData;
  } catch (error) {
    throw error;
  }
}

*/
