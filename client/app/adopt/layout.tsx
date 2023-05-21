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
        textContent={"플라스틱 육아 시작하기"}
        secretButton={true}
      />
      {children}
      <Navbar />
    </>
  );
}
