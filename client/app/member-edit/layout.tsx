import Header from "../Header";
import Navbar from "../Navbar";

export default function MemberEditLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <Header
        backButton={true}
        textContent={null}
        voteButton={false}
        withdrawalButton={true}
      />
      {children}
      <Navbar />
    </>
  );
}
