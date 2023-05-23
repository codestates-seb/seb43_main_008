"use client";

import { useRouter } from "next/navigation";
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

  voteButton,
}: {
  backButton: boolean;
  textContent: boolean | string;
  secretButton: boolean; // secretButton을 optional로 변경
  voteButton?: boolean; // voteButton을 optional로 변경
}) {
  const [isVoteModalOpen, setIsVoteModalOpen] = useState(false);
  const [isVotePublic, setIsVotePublic] = useState(false);
  const router = useRouter();

  return (
    <HeaderContainer onClick={() => router.back()}>
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

      <VoteButtonContainer>
        {voteButton && (
          <VoteButton onClick={() => setIsVoteModalOpen(true)}>
            <VoteButtonContent>
              <Image
                src={"/vote.svg"}
                alt={"투표 버튼입니다"}
                width={20}
                height={20}
              />

              <VoteButtonText>투표 신청</VoteButtonText>
            </VoteButtonContent>
          </VoteButton>
        )}
      </SecretButtonContainer>

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
