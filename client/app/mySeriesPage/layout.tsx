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
        textContent={"봉다리 세바스찬"}
        voteButton={true}
      />
      {children}
      <Navbar />
    </>
  );
}
