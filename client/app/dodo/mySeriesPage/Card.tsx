"use client";

import styled from "styled-components";

// 콘텐츠 박스 디테일 레이아웃
const ContentsContainer = styled.div`
  background-color: white;
  margin-top: 25px;
  display: flex;
  align-items: center;
  padding: 20px 24px;
  border: 1px, solid #757575;
  flex-direction: column;
  border-radius: 10px;
`;

const ImgContents = styled(ContentsContainer)`
  border: 1px solid #9b9ba0;
  font-size: 12px;
  color: #222;
  width: 95%;
  height: 400px;
  /* flex-direction: column; */
  /* position: relative; */
`;

const SeriesContents = styled(ContentsContainer)`
  font-size: 16px;
  color: #222;
`;

const BottomContainer = styled.div`
  display: flex;
  align-items: center;
  align-self: flex-start;
`;

const WriteTime = styled(BottomContainer)``;

export default function Card() {
  return (
    <ContentsContainer>
      <ImgContents />
      <SeriesContents>
        가만 보면 너는 진짜 너무해 때론 날 무심하게 내팽게 친 채 불안함이란 벌을
        내려줘 그럴 때면 난 길을 잃은 강아지가 돼 고개를 푹 숙인 채 걷네 그냥 난
        네 품에 안겨있고 싶은데 날이 선 너의 말들에 찔려서 피가 났어 난 근데
        걱정은커녕 혼자 낫는 법을 너는 알려줘 그러다가도 헷갈리게 가끔은 예쁨을
        내게 줘 그럴 때면 나는 어떻게 해야 될지 몰라 난 와르르 무너지고 말아
        나의 맘은 와르르 내게 이러지마 baby 너 없는 하루는 상상하기 조차 싫은 걸
        아무것도 할 수 없을 걸 난 바보가 돼버릴거야~
      </SeriesContents>
      <BottomContainer>
        <WriteTime
          style={{
            fontSize: "12px;",
            color: "#757575;",
            // opacity: "0.9;",
            marginLeft: "45px;",
          }}
        >
          2023년 5월 9일
        </WriteTime>
      </BottomContainer>
    </ContentsContainer>
  );
}
