"use client";

import React, { useState } from "react";
import TextareaAutosize from "react-textarea-autosize";
import styled from "styled-components";

// export default function WriteInput({ onChildValue }) {
export default function WriteInput({}) {
  const [text, setText] = useState("");

  const handleChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    setText(event.target.value);
  };

  return (
    <Container>
      <PlaceholderLabel
        htmlFor="writeInputDetail"
        style={{
          opacity: text ? "0" : "1",
          textAlign: "center",
        }}
      >
        노 모어 플라스틱, 더 클린 서울
        <br />
        여기에 시리즈를 작성해 주세요.
      </PlaceholderLabel>
      <WriteInputDetail
        id="writeInputDetail"
        value={text}
        onChange={handleChange}
        autoFocus
      />
    </Container>
  );
}
const Container = styled.div`
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 160px;
`;

const WriteInputDetail = styled(TextareaAutosize)`
  color: #222;
  border: none;
  padding: 10px;
  overflow: hidden;
  resize: none;
  /* width: 100%; */
  font-size: 14px;
  overflow: hidden;
  max-height: 150px;
  &:focus {
    outline: none;
  }
  width: 279px;
  height: 160px;
`;

const PlaceholderLabel = styled.label`
  position: absolute;
  pointer-events: none;
  color: gray;
  font-size: 16px;
  transition: 0.3s;
  width: 279px;
  height: 160px;
  top: 90%; // Container의 상단으로부터 50% 떨어져랏
  left: 50%; // Container의 왼쪽으로부터 50% 떨어져랏
  transform: translate(-50%, -50%); // 위치를 조정하여 중앙에 배치
`;
