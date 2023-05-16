"use client";

import styled from "styled-components";

import CountCard from "./CountCard";
import { Slider } from "./Slider";

export default function page() {
  return (
    <>
      <MainSeriesContainer>
        <CountCard />
        <Slider />
      </MainSeriesContainer>
    </>
  );
}
// 페이지 기본 레이아웃
const MainSeriesContainer = styled.div`
  box-sizing: border-box;
  padding: 1px 24px;
  max-width: 1024px;
  margin: 0 auto;
  background-color: #fafafa;
  min-height: 550px;
  min-width: 320px;

  @media screen and (max-width: 768px) {
  }
`;
