"use client";

import Image from "next/image";
import React from "react";
import styled from "styled-components";

import Header from "./Header";
import NameInput from "./NameInput";
import Navbar from "./Navbar";

// 페이지 기본 레이아웃
const BodyContainer = styled.div`
  padding-top: 1px;
  max-width: 1024px;
  margin: 0 auto;
  background-color: #fafafa;
  min-height: 90vh;
  min-width: 390px;

  @media screen and (max-width: 768px) {
  }
`;

const MainContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  height: 80vh; // 필요에 따라 위치 조정
`;

const MainText = styled.p`
  font-size: 26px;
  margin-top: 200px;
`;

export default function page() {
  return (
    <>
      <Header
        backButton={true}
        textContent={"플라스틱 육아 시작하기"}
        saveButton={true}
      />
      <BodyContainer>
        <MainContainer>
          <Image src="/logo.png" alt="로고" width="400" height="400" />
          <MainText>
            육아를 시작하기에 앞서 플라스틱의 이름을 설정해 주세요!
          </MainText>
          <NameInput />
        </MainContainer>
      </BodyContainer>
      <Navbar />
    </>
  );
}
