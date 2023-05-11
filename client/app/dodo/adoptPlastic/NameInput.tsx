"use client";

import React, { useState } from "react";
import styled from "styled-components";

const names = [
  "카드값줘 체리",
  "봉다리 세바스찬",
  "집게사장의 집게발",
  "스펀지밥의 월요일",
  "나의 비닐정원",
  "밥아저씨 오뚜기밥",
]; // 배열에 원하는 이름들을 추가

// 인풋창
const InputContainer = styled.div`
  display: flex;
  justify-content: center;
`;

// placeholder
const StyleInput = styled.input`
  width: 500px;
  height: 60px;
  font-size: 18px;
  color: #222;
  padding-left: 10px;
  margin-top: 50px;

  &::placeholder {
    font-size: 18px;
    padding-left: 10px;
  }
`;

// 완료 버튼
const RandomNameButton = styled.button`
  background-color: #fcfcfd;
  color: #36395a;
  border: 1px solid rgba(34, 36, 38, 0.5);
  padding: 0.6rem 1.5rem;
  margin-left: 15px;
  border-radius: 3px;
  margin-top: 50px;
  cursor: pointer;

  &:hover {
    transform: translateY(-2px);
  }
`;

export default function NameInput() {
  const [placeholder, setPlaceholder] = useState(
    "❤️를 누르면 랜덤으로 이름을 정할수 있어요"
  );

  const generateRandomName = () => {
    const randomIndex = Math.floor(Math.random() * names.length);
    setPlaceholder(names[randomIndex]);
  };

  return (
    <InputContainer>
      <StyleInput type="text" placeholder={placeholder} />
      <RandomNameButton
        onClick={generateRandomName}
        style={{ fontSize: "16px", color: "#222" }}
      >
        ❤️
      </RandomNameButton>
    </InputContainer>
  );
}
