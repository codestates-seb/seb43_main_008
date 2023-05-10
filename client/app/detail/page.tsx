"use client";

import { Comments } from "./Comments";
import { Header } from "./Header";
import { Slider } from "./Slider";
import { Vote } from "./Vote";

export default function Detail() {
  return (
    <>
      <Header />
      <Slider />
      <Vote />
      <Comments />
    </>
  );
}
