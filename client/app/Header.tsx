"use client";

import { useState } from "react";
import { AiOutlineLeft } from "react-icons/ai";
import { AiOutlineLock } from "react-icons/ai";
import { BiArchiveOut } from "react-icons/bi";
import styled from "styled-components";

import HeaderModal from "./HeaderModal";
import VoteModal from "./VoteModal";

export default function Header({
  backButton,
  textContent,
  secretButton,
  voteButton,
}: {
  backButton: boolean;
  textContent: boolean | string;
  secretButton: boolean; // secretButton을 optional로 변경
  voteButton?: boolean; // voteButton을 optional로 변경
}) {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isPublic, setIsPublic] = useState(false);

  const [isVoteModalOpen, setIsVoteModalOpen] = useState(false);
  const [isVotePublic, setIsVotePublic] = useState(false);

  return (
    <HeaderContainer>
      {backButton ? (
        <BackArrowContainer>
          <AiOutlineLeft size="18" />
        </BackArrowContainer>
      ) : (
        <BackArrowContainer />
      )}

      {textContent ? (
        <HeaderText>
          {typeof textContent === "string"
            ? textContent
            : typeof window !== "undefined"
            ? sessionStorage.getItem("header")
            : null}
        </HeaderText>
      ) : null}

      <SecretButtonContainer>
        {secretButton && (
          <SecretButton onClick={() => setIsModalOpen(true)}>
            <SecretButtonContent>
              <AiOutlineLock size="15" />
              <SecretButtonText>공개 설정</SecretButtonText>
            </SecretButtonContent>
          </SecretButton>
        )}
        {voteButton && (
          <SecretButton onClick={() => setIsVoteModalOpen(true)}>
            <SecretButtonContent>
              <BiArchiveOut size="15" />
              <SecretButtonText>투표 가기</SecretButtonText>
            </SecretButtonContent>
          </SecretButton>
        )}
      </SecretButtonContainer>

      <HeaderModal
        isModalOpen={isModalOpen}
        setIsModalOpen={setIsModalOpen}
        isPublic={isPublic}
        setIsPublic={setIsPublic}
      />

      <VoteModal
        isVoteModalOpen={isVoteModalOpen}
        setIsVoteModalOpen={setIsVoteModalOpen}
        isVotePublic={isVotePublic}
        setIsVotePublic={setIsVotePublic}
      />
    </HeaderContainer>
  );
}

const HeaderContainer = styled.header`
  /* position: sticky;
  top: 0;
  z-index: 1; */
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  height: 44px;
  padding: 5px;
  background-color: #ffffff;
`;

const BackArrowContainer = styled.div`
  display: flex;
  align-items: center;
  height: 50px;
  width: 44px;
  padding: 5px;
  position: relative;
  cursor: pointer; // 커서를 손가락 모양으로 변경
`;

const HeaderText = styled.span`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  font-size: 14px;
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
