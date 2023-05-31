"use client";

import { useEffect, useState } from "react";
import styled from "styled-components";

import axiosInstance from "../axiosInstance";
import Badge from "./Badge";

export interface BadgeType {
  badgeId: number;
  isAcquired: boolean;
  img: string;
}

export default function Page() {
  const [badgeList, setBadgeList] = useState([]);

  useEffect(() => {
    const fetchBadgeList = async () => {
      try {
        const response = await axiosInstance.get(`/members/badge`);
        // console.log(response.data.data);
        setBadgeList(response.data.data);
      } catch (error) {
        // console.error("Error fetching badge list:", error);
      }
    };

    fetchBadgeList();
  }, []);

  return (
    <>
      <MainSeriesContainer>
        <SeriesWrapper>
          <Badge badgeList={badgeList} />
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
  background-color: white;
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
