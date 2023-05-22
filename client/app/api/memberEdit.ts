import axiosInstance from "../axiosInstance";

export async function GetProfile() {
  try {
    const response = await axiosInstance.get(`/feed`);
    return response.data.data;
  } catch (error) {
    throw error;
  }
}

export async function MemberEdit(nickName: string, introduce: string) {
  try {
    const formData = new FormData();
    formData.append("nickName", nickName);
    formData.append("introduce", introduce);
    const response = await axiosInstance.patch(`/feed`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    return response;
  } catch (error) {
    throw error;
  }
}

export async function MemberImageEdit(image: File) {
  try {
    const formData = new FormData();
    formData.append("image", image);
    const response = await axiosInstance.patch(`/feed`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    return response;
  } catch (error) {
    throw error;
  }
}
