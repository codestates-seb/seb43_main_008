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
        textContent={"획득한 뱃지 관람"}
        secretButton={false}
      />
      {children}
      <Navbar />
    </>
  );
}
