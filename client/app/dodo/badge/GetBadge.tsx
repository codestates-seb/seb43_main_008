"use client";

import React from "react";
import styled from "styled-components";

// 콘텐츠 박스 디테일 레이아웃
const ContentsContainer = styled.div`
  margin-top: 25px;
  display: flex;
  align-items: center;
  padding: 20px 24px;
  border: 1px, solid #757575;
  flex-direction: column;
  border-radius: 10px;
`;

// 중앙부 획득 뱃지 콘텐츠
const BadgeImgContents = styled(ContentsContainer)`
  border: 1px solid #9b9ba0;
  width: 300px;
  height: 300px;
  border-radius: 50%;
  background-color: #fff8de;
  margin-right: 12px;
`;

export default function GetBadge() {
  return (
    <>
      <ContentsContainer>
        <BadgeImgContents />
      </ContentsContainer>
    </>
  );
}
