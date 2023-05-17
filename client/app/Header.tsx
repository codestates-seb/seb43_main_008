"use client";

import { AiOutlineLeft } from "react-icons/ai";
import styled from "styled-components";

export default function Header({
  backButton,
  textContent,
  saveButton,
}: {
  backButton: boolean;
  textContent: string | null;
  saveButton: boolean;
}) {
  return (
    <HeaderContainer>
      {backButton ? (
        <BackArrowContainer>
          <AiOutlineLeft size="24" />
        </BackArrowContainer>
      ) : (
        <BackArrowContainer />
      )}
      {textContent ? <HeaderText>{sessionStorage?.getItem("header")}</HeaderText> : null}
      {saveButton ? (
        <SaveButtonContainer>
          <SaveButton type="submit">저장</SaveButton>
        </SaveButtonContainer>
      ) : (
        <SaveButtonContainer />
      )}
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
  height: 44px;
  width: 44px;
  padding: 10px;
`;

const HeaderText = styled.span`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  font-size: 16px;
`;

const SaveButtonContainer = styled.div`
  display: flex;
  align-items: center;
  height: 44px;
  width: 44px;
  padding: 10px 10px 10px 0;
`;

const SaveButton = styled.button`
  height: 100%;
  width: 100%;
  font-size: 13px;
  font-weight: 700;
  background: inherit;
  border: none;
  border-radius: 0;
  padding: 0;
`;
