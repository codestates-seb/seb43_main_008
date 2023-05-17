"use client";
import { useState } from "react";
import styled from "styled-components";

import BadgeCircle from "./BadgeCircle";
import BadgeDetail from "./BadgeDetail";

export default function Badge() {
  const [selectedImageDetail, setSelectedImageDetail] = useState<{
    src: string;
    alt: string;
  } | null>(null);

  return (
    <>
      <MainContainer>
        <MainText>재활용률 100%를 달성하셨네요!</MainText>
        <SubText>300%를 달성하여 아래 뱃지를 획득해보세요!</SubText>
        <BadgeCircle
          image={selectedImageDetail}
          // image={{
          //   src: "/logoSquare.png",
          //   alt: "쓰또 로고",
          // }}
        />
      </MainContainer>
      <BadgeDetail setSelectedImageDetail={setSelectedImageDetail} />
    </>
  );
}
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
