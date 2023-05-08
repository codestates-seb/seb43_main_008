import Header from "./Header";
import styles from "./MainContainer.module.css";

export default function MemberEditLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className={styles.mainContainer}>
      <Header />
      {children}
    </div>
  );
}
