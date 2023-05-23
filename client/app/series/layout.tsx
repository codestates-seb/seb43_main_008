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
        textContent={"시리즈 작성"}
        voteButton={false}
      />
      {children}
      <Navbar />
    </>
  );
}
