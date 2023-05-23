import axios from "axios";
import { useSearchParams } from "next/navigation";

export default function FetchToken() {
  const searchParams = useSearchParams();
  const accessToken = searchParams.get("Access");
  const Authorization = `Bearer ${accessToken}`;

  if (accessToken) {
    (async () => {
      try {
        const response = await axios.get(
          `${process.env.NEXT_PUBLIC_API_URL}/login/auth`,
          {
            headers: {
              Authorization: Authorization,
            },
          }
        );

        const loginToken = response.headers["authorization"];
        const refreshToken = response.headers["refresh"];

        localStorage.setItem("Authorization", loginToken);
        localStorage.setItem("RefreshToken", refreshToken);
      } catch (error) {
        console.error(error);
      }
    })();
  }
}
