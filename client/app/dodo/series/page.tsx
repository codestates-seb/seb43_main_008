"use client";

import React from "react";
import styled from "styled-components";

import WriteInput from "./WriteInput";

//////////////////
function PictureLabel() {
  return (
    <label htmlFor="picture" style={{ display: "flex", alignItems: "center" }}>
      <img
        src="/add-pic.svg"
        alt="사진 추가 아이콘"
        style={{
          width: "1200px",
          height: "500px",
          color: "#757575",
          cursor: "pointer",
        }}
      />
    </label>
  );
}
//////////////////

const MainSeriesContainer = styled.div`
  padding-top: 54px;
  max-width: 1024px;
  margin: 0 auto;
  background-color: #fafafa;
  min-height: 100vh;
  min-width: 390px;

  @media screen and (min-width: 768px) {
  }
`;

const SeriesWrapper = styled.div`
  max-width: 1000px;
  margin: 0 auto;
  padding: 30px;
`;

const InputContainer = styled.div`
  flex: 1;
  color: #222;
  background-color: white;
  display: flex;
  align-items: center;
  flex-direction: column;
  padding: 20px 24px;
  /* border: 1px solid #9b9ba0; */
  border-radius: 10px;
`;

const SubmitButton = styled.button`
  background-color: #fcfcfd;
  color: #36395a;
  border: 1px solid rgba(34, 36, 38, 0.5);
  padding: 0.6rem 1.5rem;
  margin-left: 15px;
  border-radius: 3px;
  cursor: pointer;

  &:hover {
    transform: translateY(-2px);
  }
`;

const UploadImageContainer = styled.div`
  background-color: white;
  margin-top: 25px;
  margin-bottom: 25px;
  display: flex;
  align-items: center;
  padding: 20px 24px;
  flex-direction: column;
  border-radius: 10px;
  border: 1px solid #9b9ba0;
`;

export default function page() {
  return (
    <>
      <MainSeriesContainer>
        <h1>시리즈를 작성해주세요</h1>
        <SeriesWrapper>
          <InputContainer>
            <UploadImageContainer>
              {/* ///////////////////// */}
              <PictureLabel />
              <input type="file" id="picture" style={{ display: "none" }} />
              {/* ///////////////////// */}
            </UploadImageContainer>
            <WriteInput />
            <SubmitButton type="submit">ok</SubmitButton>
          </InputContainer>
        </SeriesWrapper>
      </MainSeriesContainer>
    </>
  );
}
