"use client";

import styled from "styled-components";
import { Header } from "./Header";
import { Slider } from "./Slider";
import { Vote } from "./Vote";
import { Comment } from "./Comment";

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
