"use client";
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
        textContent={"획득한 뱃지 열람"}
        voteButton={false}
      />
      {children}
      <Navbar />
    </>
  );
}
