"use client";

import React, { useState } from "react";
import TextareaAutosize from "react-textarea-autosize";
import styled from "styled-components";

const Container = styled.div`
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
`;

const WriteInputDetail = styled(TextareaAutosize)`
  color: #222;
  border: none;
  padding: 10px;
  overflow: hidden;
  resize: none;
  width: 100%;
  font-size: 18px;
  &:focus {
    outline: none;
  }
`;

const PlaceholderLabel = styled.label`
  position: absolute;
  pointer-events: none;
  color: gray;
  font-size: 18px;
  transition: 0.3s;
`;

export default function WriteInput() {
  const [text, setText] = useState("");

  const handleChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    setText(event.target.value);
  };

  return (
    <Container>
      <PlaceholderLabel
        htmlFor="writeInputDetail"
        style={{ opacity: text ? "0" : "1" }}
      >
        노 모어 플라스틱, 더 클린 서울을 위해서 시리즈를 작성해 주세요~
      </PlaceholderLabel>
      <WriteInputDetail
        id="writeInputDetail"
        value={text}
        onChange={handleChange}
      />
    </Container>
  );
}
