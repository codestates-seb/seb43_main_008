"use client";

import React from "react";
import { AiOutlineLeft } from "react-icons/ai";
import styled from "styled-components";

export default function Header({
  backButton,
  textContent,
}: {
  backButton: boolean;
  textContent: string | null;
}) {
  return (
    <HeaderContainer>
      {backButton ? (
        <BackArrowContainer>
          <AiOutlineLeft size="18" />
        </BackArrowContainer>
      ) : (
        <BackArrowContainer />
      )}
      {textContent ? <HeaderText>{textContent}</HeaderText> : null}
      <EmptyContainer />
    </HeaderContainer>
  );
}

const HeaderContainer = styled.header`
  position: sticky;
  top: 0;
  display: flex;
  justify-content: space-between;
  height: 44px;
  background-color: #ffffff;
  z-index: 1;
`;

const BackArrowContainer = styled.div`
  display: flex;
  align-items: center;
  height: 50px;
  width: 44px;
  padding: 10px;

  cursor: pointer;
`;

const EmptyContainer = styled.div`
  width: 44px;
  height: 50px;
`;

const HeaderText = styled.span`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  font-size: 13px;
  margin-top: 3px;
`;
