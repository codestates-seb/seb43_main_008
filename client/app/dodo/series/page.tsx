"use client";

import styled from "styled-components";

import Header from "./Header";
import UploadImg from "./UploadImg";
import WriteInput from "./WriteInput";

// 페이지 기본 레이아웃
const MainSeriesContainer = styled.div`
  padding-top: 1px;
  max-width: 1024px;
  margin: 0 auto;
  background-color: #fafafa;
  min-height: 100vh;
  min-width: 390px;

  /* @media screen and (min-width: 768px) { */
  @media screen and (max-width: 768px) {
  }
`;

// 사진+글 업로드 레이아웃
const SeriesWrapper = styled.div`
  margin: 16px 24px;
  display: flex;
  flex-direction: column;
  background-color: white;
  border-radius: 8px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
`;

// 사진 + 글 업로드 디테일 레이아웃
const WrapperDetailContainer = styled.div`
  flex: 1;
  color: #222;
  background-color: white;
  display: flex;
  align-items: center;
  flex-direction: column;
  padding: 30px 24px;
  border-radius: 10px;
`;

export default function page() {
  return (
    <>
      <Header backButton={true} textContent={"시리즈 작성"} saveButton={true} />
      <MainSeriesContainer>
        <SeriesWrapper>
          <WrapperDetailContainer>
            <UploadImg />
            <WriteInput />
          </WrapperDetailContainer>
        </SeriesWrapper>
      </MainSeriesContainer>
    </>
  );
}
