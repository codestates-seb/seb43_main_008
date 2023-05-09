import Image from "next/image";

import styles from "./pageContainer.module.css";
import SocialLoginButtonBox from "./SocialLoginButtonBox";

export default function Login() {
  return (
    <div className={styles.pageContainer}>
      <Image
        src="/logoSquare.png"
        alt="App Main Logo"
        width="130"
        height="30"
      />
      <SocialLoginButtonBox />
    </div>
  );
}
