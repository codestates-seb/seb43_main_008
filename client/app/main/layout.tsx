import Navbar from "./Navbar";

export default function MemberEditLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      {children}
      <Navbar />
    </>
  );
}