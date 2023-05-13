import { Filter } from "./main/Filter"
import { Header } from "./main/Header";
import { Lists } from "./main/Lists"
import styles from "./main/page.module.css";
import { Scroll } from "./main/Scroll"


export default function Main() {
  return (
    <main className={`${styles.container}`} >
      <Header />
      <Filter />
      <Lists />
      <Scroll />
    </main>
  );
}
