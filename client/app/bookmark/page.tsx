import { Lists } from "./Lists"
import styles from "./page.module.css";
import { Scroll } from "./Scroll"



export default function Main() {
  return (
    <main className={`${styles.container}`} >
      <Lists />
      <Scroll />
    </main>
  );
}
