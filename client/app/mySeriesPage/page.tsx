"use client";

import styled from "styled-components";

import Card from "./Card";
import CountCard from "./CountCard";

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

// 콘텐츠 박스 레이아웃
const SeriesWrapper = styled.div`
  margin: 7px 1px;
`;

export default function page() {
  return (
    <>
      <MainSeriesContainer>
        <SeriesWrapper>
          <CountCard />
          <Card />
        </SeriesWrapper>
      </MainSeriesContainer>
    </>
  );
}
