"use client";

import styled from "styled-components";

import Card from "./Card";
import CountCard from "./CountCard";
import Header from "./Header";
// import Navbar from "./Navbar";

// 페이지 기본 레이아웃
const MainSeriesContainer = styled.div`
  padding-top: 1px;
  max-width: 1024px;
  margin: 0 auto;
  background-color: #fafafa;
  min-height: 100vh;
  min-width: 390px;
  overflow: hidden;

  @media screen and (max-width: 768px) {
  }
`;

// 콘텐츠 박스 레이아웃
const SeriesWrapper = styled.div`
  margin: 0 1px;
  padding: 10px;
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
      {/* <Navbar /> */}
    </>
  );
}
