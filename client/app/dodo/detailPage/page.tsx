"use client";

import React from "react";
import styled from "styled-components";

const MainSeriesContainer = styled.div`
  padding-top: 54px;
  max-width: 1024px;
  margin: 0 auto;
  background-color: #fafafa;
  min-height: 100vh;

  /* @media screen and (min-width: 768px) {
  .list {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    flex-wrap: wrap;
  } */
`;

const SeriesWrapper = styled.div`
  width: 1000px;
  margin: 0 auto;
  padding: 30px;
`;

const SeriesList = styled(SeriesWrapper)`
  width: 729px;
`;

// const PlasticList = styled.div``;

const InputContainer = styled.div`
  flex: 1;
  color: #222;
  background-color: white;
  display: flex;
  align-items: center;
  padding: 20px 24px;
  border: 1px, solid #757575;
  border-radius: 10px;
`;

const WriteInput = styled.input`
  font-size: 12px;
  width: 100%;
  color: #9b9ba0;
  border: none;
  padding: 10px;
  &:focus {
    outline: none;
  }
`;

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
  width: 90%;
  height: 300px;
`;

const SeriesContents = styled(ContentsContainer)`
  font-size: 12px;
  color: #222;
`;

const BottomContainer = styled.div`
  /* width: 32px; */
  display: flex;
  align-items: center;
  align-self: flex-start;
`;

const WriteTime = styled(BottomContainer)``;

export default function page() {
  return (
    <>
      <MainSeriesContainer>
        <SeriesWrapper>
          <SeriesList>
            <InputContainer>
              <WriteInput
                type="text"
                placeholder="사용한 횟수"
                style={{ fontSize: "15px;" }}
              />
            </InputContainer>
            <ContentsContainer>
              <ImgContents />
              <SeriesContents>
                가만 보면 너는 진짜 너무해 때론 날 무심하게 내팽게 친 채
                불안함이란 벌을 내려줘 그럴 때면 난 길을 잃은 강아지가 돼 고개를
                푹 숙인 채 걷네 그냥 난 네 품에 안겨있고 싶은데 날이 선 너의
                말들에 찔려서 피가 났어 난 근데 걱정은커녕 혼자 낫는 법을 너는
                알려줘 그러다가도 헷갈리게 가끔은 예쁨을 내게 줘 그럴 때면 나는
                어떻게 해야 될지 몰라 난 와르르 무너지고 말아 나의 맘은 와르르
                내게 이러지마 baby 너 없는 하루는 상상하기 조차 싫은 걸 아무것도
                할 수 없을 걸 난 바보가 돼버릴거야~
              </SeriesContents>
              <BottomContainer>
                <WriteTime
                  style={{
                    fontSize: "10px;",
                    color: "#757575;",
                    // opacity: "0.9;",
                    marginLeft: "45px;",
                  }}
                >
                  2023년 5월 9일
                </WriteTime>
              </BottomContainer>
            </ContentsContainer>
            {/* ////////////////// */}
            <ContentsContainer>
              <ImgContents />
              <SeriesContents>
                왜들 그리 다운돼있어 뭐가 문제야 say something 분위기가 겁나
                싸해 요새는 이런 게 유행인가 왜들 그리 재미없어 아 그건 나도
                마찬가지 Tell me what I got to do 급한 대로 블루투스 켜 아무
                노래나 일단 틀어 아무거나 신나는 걸로 아무렇게나 춤춰 아무렇지
                않아 보이게 아무 생각 하기 싫어 아무개로 살래 잠시 I’m sick and
                tired of my everyday Keep it up 한 곡 더!
              </SeriesContents>
              <BottomContainer>
                <WriteTime
                  style={{
                    fontSize: "10px;",
                    color: "#757575;",
                    // opacity: "0.9;",
                    marginLeft: "25px;",
                  }}
                >
                  2023년 5월 8일
                </WriteTime>
              </BottomContainer>
            </ContentsContainer>

            <ContentsContainer>
              <ImgContents />
              <SeriesContents>
                어쩜 이렇게 하늘은 더 파란 건지 오늘따라 왜 바람은 또 완벽한지
                그냥 모르는 척 하나 못들은 척 지워버린 척 딴 얘길 시작할까 아무
                말 못하게 입맞출까 눈물이 차올라서 고갤 들어 흐르지 못하게 또
                살짝 웃어 내게 왜 이러는지 무슨 말을 하는지 오늘 했던 모든 말 저
                하늘 위로 한번도 못했던 말 울면서 할 줄은 나 몰랐던 말 나는요
                오빠가 좋은걸 어떡해 새로 바뀐 내 머리가 별로였는지 입고 나왔던
                옷이 실수였던 건지 아직 모르는 척 기억 안 나는 척 아무 일없던
                것처럼 굴어볼까 그냥 나가자고 얘기할까 눈물이 차올라서 고갤 들어
                흐르지 못하게 또 살짝 웃어 내게 왜 이러는지 무슨 말을 하는지
                오늘 했던 모든 말 저 하늘 위로 한번도 못했던 말 울면서 할 줄은
                나 몰랐던 말 나는요 오빠가 좋은걸 어떡해.
              </SeriesContents>
              <BottomContainer>
                <WriteTime
                  style={{
                    fontSize: "10px;",
                    color: "#757575;",
                    // opacity: "0.9;",
                    marginLeft: "25px;",
                  }}
                >
                  2023년 5월 7일
                </WriteTime>
              </BottomContainer>
            </ContentsContainer>

            <ContentsContainer>
              <ImgContents />
              <SeriesContents>
                아직 잊지 못하고 아주 달콤히 맴돌아 나 꿈인 줄 모르고 star
                헤매이고 있어 그냥 어지러운 가봐요 잠이 든 그댄 아무 말도 말아요
                이미 익숙해진 우리 미로 사이 참을 수 없어 Have a feel so sweet
                어색했던 우리 첫 와인처럼 쓰고 아프지만 두 눈에 가득 담아 흐르는
                대로 널 보내줄게 나 너에게 취해 난 이 밤에 취해 니가 모질게 했던
                그 기억 속에 그 추억에 헤매 널 찾고 있어 그냥 잊을래 너의 맘도
                알고 싶어 boy 그냥 잊어줘.
              </SeriesContents>
              <BottomContainer>
                <WriteTime
                  style={{
                    fontSize: "10px;",
                    color: "#757575;",
                    // opacity: "0.9;",
                    marginLeft: "25px;",
                  }}
                >
                  2023년 5월 6일
                </WriteTime>
              </BottomContainer>
            </ContentsContainer>
            {/* ////////////////// */}
          </SeriesList>
          {/* <PlasticList>플라스틱</PlasticList> */}
        </SeriesWrapper>
      </MainSeriesContainer>
    </>
  );
}
