import axios from "axios";

const Authorization =
  typeof window !== "undefined" ? localStorage.getItem("Authorization") : null;

export const memberEdit = async (nickname: string, introduce: string) => {
  try {
    const response = await axios.patch(
      `${process.env.NEXT_PUBLIC_API_URL}/login/auth`,
      { nickname, introduce },
      {
        headers: {
          Authorization: Authorization,
        },
      }
    );
    return response.data;
  } catch (error) {
    throw error;
  }
};
