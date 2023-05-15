"use client";

import styled from "styled-components";

import Card from "./Card";
import CountCard from "./CountCard";
import Header from "./Header";

// 페이지 기본 레이아웃
const MainSeriesContainer = styled.div`
  padding: 1px 24px;
  max-width: 1024px;
  margin: 0 auto;
  background-color: #fafafa;
  min-height: 100vh;
  min-width: 368px;
  overflow: hidden;

  @media screen and (max-width: 768px) {
  }
`;

// 콘텐츠 박스 레이아웃
const SeriesWrapper = styled.div`
  margin: 7px 1px;
`;

export default function page() {
  return (
    <>
      <Header
        backButton={true}
        textContent={"봉다리 세바스찬"}
        mainButton={true}
      />
      <MainSeriesContainer>
        <SeriesWrapper>
          <CountCard />
          <Card />
        </SeriesWrapper>
      </MainSeriesContainer>
    </>
  );
}
