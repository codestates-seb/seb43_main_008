"use client";

import React from "react";
import styled from "styled-components";

import BadgeDetail from "./BadgeDetail";
import GetBadge from "./GetBadge";
import Header from "./Header";

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
const MainText = styled.p`
  font-size: 26px;
  margin-top: 120px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const SubText = styled.p`
  font-size: 26px;
  margin-top: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

export default function page() {
  return (
    <>
      <Header
        backButton={true}
        textContent={"획득한 뱃지 관람"}
        mainButton={true}
      />
      <MainSeriesContainer>
        <SeriesWrapper>
          <MainText>재활용률 100%를 달성하셨네요!</MainText>
          <SubText>300%를 달성하여 아래 뱃지를 획득해보세요!</SubText>
          <GetBadge />
          <BadgeDetail />
        </SeriesWrapper>
      </MainSeriesContainer>
    </>
  );
}
