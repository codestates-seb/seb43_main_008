import Header from "../Header";
import Navbar from "../Navbar";

export default function MemberEditLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <Header backButton={true} textContent={"마이페이지"} secretButton={false} />
      {children}
      <Navbar />
    </>
  );
}
