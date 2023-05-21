"use client";

import Image from "next/image";
import styled from "styled-components";

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

// '메인텍스트+서브텍스트+중안부 동그라미 뱃지' 포함한 박스 레이아웃 스타일
const MainContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  height: 50vh;
`;

// 중앙부 뱃지 콘텐츠 스타일
const BadgeImgContents = styled.div`
  border: 1px solid #9b9ba0;
  width: 240px;
  height: 240px;
  border-radius: 50%;
  background-color: #fff8de;
  margin-top: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 20px;
`;

export default function page() {
  return (
    <>
      <MainSeriesContainer>
        <MainContainer>
          <BadgeImgContents>
            <Image src="/logoSquare.png" alt="로고" width="190" height="300" />
          </BadgeImgContents>
        </MainContainer>
      </MainSeriesContainer>
    </>
  );
}
