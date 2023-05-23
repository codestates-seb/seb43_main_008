import Header from "../Header";
import Navbar from "../Navbar";

export default function MemberEditLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <Header backButton={true} textContent={"팔로워"} voteButton={true} />
      {children}
      <Navbar />
    </>
  );
}
