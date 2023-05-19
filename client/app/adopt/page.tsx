"use client";

import Image from "next/image";
import React from "react";
import { useState } from "react";
import styled from "styled-components";

import NameInput from "./NameInput";

export default function Page() {
  const [a, setA] = useState(1);

  return (
    <>
      <BodyContainer>
        <MainContainer>
          <Image src="/logo.png" alt="로고" width="190" height="300" />
          <MainText style={{ textAlign: "center" }}>
            육아를 시작하기에 앞서
            <br />
            플라스틱의 이름을 설정해 주세요!
          </MainText>
          <NameInput />
          <SubmitButton>입양 완료</SubmitButton>
        </MainContainer>
      </BodyContainer>
    </>
  );
}

// 페이지 기본 레이아웃
const BodyContainer = styled.div`
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

const MainContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;

  height: 55vh; // 필요에 따라 위치 조정
`;

const MainText = styled.p`
  font-size: 18px;
  margin-top: 50px;
`;

const SubmitButton = styled.button`
  background-color: #fcfcfd;
  color: #222;
  border: 1px solid rgba(34, 36, 38, 0.5);
  padding: 0.6rem 0.6rem;
  border-radius: 3px;
  margin-top: 10px;
  min-width: 325px;
  cursor: pointer;

  &:active {
    transform: translateY(-2px);
  }
`;

// 플레이스 홀더 좀 다시 정리하고 싶음
// 랜덤이름 100만가지 양산 하기
