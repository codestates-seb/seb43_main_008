"use client";
import { useState } from "react";
import styled from "styled-components";

import BadgeCircle from "./BadgeCircle";
import BadgeDetail from "./BadgeDetail";

export default function Badge() {
  // 뱃지 아이콘 관리
  const [selectedImageDetail, setSelectedImageDetail] = useState<{
    src: string;
    alt: string;
    // } | null>(null);
  } | null>({
    src: "/logoSquare.png", // 초기 이미지의 경로가져오기
    alt: "쓰또 로고",
  });
  // const [isAcquired, setIsAcquired] = useState<boolean>(true);

  // 뱃지 획득 방법 설명 텍스트 관리
  const [mainText, setMainText] = useState("쓰쓰또쓰 가입을 환영합니다!");
  const [subText, setSubText] = useState(
    "'쓰쓰또쓰의 다양한 뱃지들을 획득해보세요!"
  );

  // 뱃지 세피아 처리
  const [isAcquired, setIsAcquired] = useState(false);
  return (
    <>
      <MainContainer>
        <MainText>{mainText}</MainText>
        <SubText>{subText}</SubText>
        <BadgeCircle image={selectedImageDetail} isAcquired={isAcquired} />
      </MainContainer>
      {/* <BadgeDetail setSelectedImageDetail={setSelectedImageDetail} /> */}
      <BadgeDetail
        setSelectedImageDetail={setSelectedImageDetail}
        setMainText={setMainText}
        setSubText={setSubText}
        setIsAcquired={setIsAcquired}
      />
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
