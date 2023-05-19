"use client";

import { useState } from "react";
import { AiOutlineLeft } from "react-icons/ai";
import { VscGistSecret } from "react-icons/vsc";
import styled from "styled-components";

import HeaderModal from "./HeaderModal";

export default function Header({
  backButton,
  textContent,
  secretButton,
}: {
  backButton: boolean;
  textContent: boolean | string;
  secretButton: boolean;
}) {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isPublic, setIsPublic] = useState(false);

  return (
    <HeaderContainer>
      {backButton ? (
        <BackArrowContainer>
          <AiOutlineLeft size="18" />
        </BackArrowContainer>
      ) : (
        <BackArrowContainer />
      )}

      {textContent ? <HeaderText>{typeof textContent === "string" ? textContent : typeof window !== 'undefined' ? sessionStorage.getItem('header') : null}</HeaderText> : null}

      {secretButton ? (
        <SecretButtonContainer onClick={() => setIsModalOpen(true)}>
          <SecretButton>
            <SecretButtonContent>
              <VscGistSecret size="15" />
              <SecretButtonText>공개 설정</SecretButtonText>
            </SecretButtonContent>
          </SecretButton>
        </SecretButtonContainer>
      ) : (
        <SecretButtonContainer />
      )}
      <HeaderModal
        isModalOpen={isModalOpen}
        setIsModalOpen={setIsModalOpen}
        isPublic={isPublic}
        setIsPublic={setIsPublic}
      />
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
  position: relative;
  cursor: pointer; // 커서를 손가락 모양으로 변경
`;

const HeaderText = styled.span`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  font-size: 13px;
  margin-top: 3px;
`;

const SecretButtonContainer = styled.div`
  display: flex;
  align-items: center;
  height: 50px;
  width: 50px;
  padding: 10px 10px 10px 0;
  margin-top: 3px;
`;

const SecretButton = styled.button`
  height: 100%;
  width: 100%;
  background: inherit;
  border: none;
  border-radius: 0;
  padding: 0;
`;

const SecretButtonContent = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
  width: 100%;
`;

const SecretButtonText = styled.span`
  font-size: 6px;
`;
