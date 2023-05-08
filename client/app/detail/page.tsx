"use client";

import { Comment } from "./Comment";
import { Header } from "./Header";
import { Slider } from "./Slider";
import { Vote } from "./Vote";

export default function Detail() {
  return (
    <>
      <Header />
      <Slider />
      <Vote />
      <Comment />
    </>
  );
}
