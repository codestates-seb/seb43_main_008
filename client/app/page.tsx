import { Filter } from "./main/Filter";
import { Lists } from "./main/Lists";
import styles from "./main/page.module.css";
import { Scroll } from "./main/Scroll";
import Navbar from "./Navbar";
import { Header } from "./Header";

export default function Main() {
  return (
    <>
      <main className={`${styles.container}`}>
        <Header />
        <Filter />
        <Lists />
        <Scroll />
      </main>
      <Navbar />
    </>
  );
}
