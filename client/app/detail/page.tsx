"use client";

import styled from "styled-components";
import { Header } from "./Header";
import { Slider } from "./Slider";
import { Vote } from "./Vote";
import { StyledCard } from "./Card";

export default function Detail() {
  return (
    <>
      <Header />
      <Slider />
      <Vote />
      <h3 className="sub-title">댓글 달기</h3>
      <StyledCard className="comment">
        <h3 className="sub-title">댓글 달기</h3>
      </StyledCard>
    </>
  );
}
