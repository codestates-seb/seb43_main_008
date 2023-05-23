import Image from "next/image";

import LoginContext from "./LoginContext";
import styles from "./pageContainer.module.css";
import SocialLoginButtonBox from "./SocialLoginButtonBox";

export default function Login() {
  return (
    <div className={`${styles["login-page"]} header-page-container`}>
      <Image
        src="/logoSquare.png"
        alt="쓰쓰또쓰 로고"
        width="100"
        height="30"
      />
      <LoginContext />
      <SocialLoginButtonBox />
    </div>
  );
}
