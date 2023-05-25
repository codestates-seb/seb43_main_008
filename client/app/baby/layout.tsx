"use client";

import Navbar from "../Navbar";

export default function SeriesLayout({
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
