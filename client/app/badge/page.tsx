"use client";

import styled from "styled-components";

import Badge from "./Badge";
// import styles from "./page.module.css";

export default function page() {
  return (
    <>
      <MainSeriesContainer>
        <SeriesWrapper>
          <Badge />
        </SeriesWrapper>
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

// 콘텐츠 박스 레이아웃
const SeriesWrapper = styled.div`
  margin: 0 1px;
  padding: 2px;
`;
