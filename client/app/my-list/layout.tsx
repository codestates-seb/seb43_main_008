"use client";
import Header from "../Header";
import Navbar from "../Navbar";

export default function SeriesLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <Header
        backButton={true}
        textContent={"시리즈 상세 조회"}
        // secretButton={false}
        voteButton={true}
      />
      {children}
      <Navbar />
    </>
  );
}
