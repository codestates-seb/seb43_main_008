import { Lists } from "./Lists"
import styles from "./page.module.css";

export default function Main() {
  return (
    <main className={`${styles.container}`} >
      <Lists />
    </main>
  );
}
