import { Header } from "./main/Header";
import { Lists } from "./main/Lists";
import styles from "./main/page.module.css";
import Navbar from "./Navbar";

export default function Main() {
  return (
    <>
      <main className={`${styles.container}`}>
        <Header />
        <Lists />
      </main>
      <Navbar />
    </>
  );
}
