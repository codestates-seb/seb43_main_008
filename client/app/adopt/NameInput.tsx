"use client";

import React, { useState } from "react";
import styled from "styled-components";

import names from "./Names";

export default function NameInput() {
  const [inputValue, setInputValue] = useState("");
  const [placeholder, setPlaceholder] = useState(
    "❤️를 누르면 랜덤으로 이름을 정할수 있어요"
  );

  const generateRandomName = () => {
    const randomIndex = Math.floor(Math.random() * names.length);
    setPlaceholder("  " + names[randomIndex]); // 마우스 커서와 랜덤이름 갭주기
  };

  const handleInputChange = (event: {
    target: { value: React.SetStateAction<string> };
  }) => {
    setInputValue(event.target.value);
    setInput(event.target.value);
  };

  return (
    <InputContainer>
      <StyleInput
        type="text"
        value={inputValue}
        placeholder={placeholder}
        onClick={generateRandomName}
        onChange={handleInputChange}
      />
    </InputContainer>
  );
}

const InputContainer = styled.div`
  display: flex;
  justify-content: center;
`;

// 플레이스홀더의 특정 부분에 대해 이벤트 리스너를 설정하는 것은 불가능 -> 아오 빡쳐 ㅜ
const StyleInput = styled.input`
  width: 260px;
  height: 40px;
  font-size: 14px;
  color: #222;
  padding-left: 10px;
  margin-top: 30px;
  text-align: center;
  background-color: #fcfcfd;
  border: 1px solid rgba(34, 36, 38, 0.5);
  padding: 0.6rem 0.6rem;
  border-radius: 3px;
  min-width: 325px;

  &:focus {
    text-align: left;
  }

  &::placeholder {
    font-size: 14px;
  }
`;
function setInput(value: React.SetStateAction<string>) {
  throw new Error("Function not implemented.");
}
