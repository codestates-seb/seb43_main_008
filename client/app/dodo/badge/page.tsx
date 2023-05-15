"use client";

import React from "react";
import styled from "styled-components";

import Navbar from "../../Navbar";
import BadgeDetail from "./BadgeDetail";
import GetBadge from "./GetBadge";
import Header from "./Header";

// 페이지 기본 레이아웃
const MainSeriesContainer = styled.div`
  padding: 1px 24px;
  max-width: 1024px;
  margin: 0 auto;
  background-color: #fafafa;
  min-height: 90vh;
  min-width: 368px;

  @media screen and (max-width: 768px) {
  }
`;

// 콘텐츠 박스 레이아웃
const SeriesWrapper = styled.div`
  margin: 0 1px;
  padding: 2px;
`;

const MainContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  height: 50vh; // 필요에 따라 위치 조정
`;

const MainText = styled.p`
  font-size: 15px;
  margin-top: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const SubText = styled.p`
  font-size: 16px;
  margin-top: 15px;
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
        // mainButton={true}
      />
      <MainSeriesContainer>
        <SeriesWrapper>
          <MainContainer>
            <MainText>재활용률 100%를 달성하셨네요!</MainText>
            <SubText>300%를 달성하여 아래 뱃지를 획득해보세요!</SubText>
            <GetBadge />
          </MainContainer>
          <BadgeDetail />
        </SeriesWrapper>
      </MainSeriesContainer>
      <Navbar />
    </>
  );
}
