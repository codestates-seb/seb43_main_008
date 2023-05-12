
import { Header } from './Header';
import { Lists } from "./Lists"
import styles from "./page.module.css";
import { Scroll } from "./Scroll"



export default function Main() {
  return (
    <main className={`${styles.container}`} >
      <Header />
      <Lists />
      <Scroll />
    </main>
  );
}