"use client";

import Image from "next/image";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { AiOutlineLeft } from "react-icons/ai";
import styled from "styled-components";

import VoteModal from "./VoteModal";
import WithdrawlModal from "./WithdrawlModal";

export default function Header({
  backButton,
  textContent,
  voteButton,
  withdrawalButton,
}: {
  backButton: boolean;
  textContent: boolean | string;
  // secretButton: boolean; // secretButton을 optional로 변경
  voteButton: boolean; // voteButton을 optional로 변경
  withdrawalButton?: boolean;
}) {
  const [isWithdrawlModalOpen, setIsWithdrawlModalOpen] = useState(false);
  const [isVoteModalOpen, setIsVoteModalOpen] = useState(false);
  const [isVotePublic, setIsVotePublic] = useState(false);
  const router = useRouter();

  return (
    <HeaderContainer>
      {backButton ? (
        <BackArrowContainer onClick={() => router.back()}>
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
        {withdrawalButton && (
          <VoteButton onClick={() => setIsWithdrawlModalOpen(true)}>
            탈퇴
          </VoteButton>
        )}
      </VoteButtonContainer>

      <VoteModal
        isVoteModalOpen={isVoteModalOpen}
        setIsVoteModalOpen={setIsVoteModalOpen}
        isVotePublic={isVotePublic}
        setIsVotePublic={setIsVotePublic}
      />

      <WithdrawlModal
        isWithdrawlModalOpen={isWithdrawlModalOpen}
        setIsWithdrawlModalOpen={setIsWithdrawlModalOpen}
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

const VoteButtonContainer = styled.div`
  display: flex;
  align-items: center;
  height: 50px;
  width: 50px;
  padding: 10px 10px 10px 0;
  margin-top: 3px;
`;

const VoteButton = styled.button`
  height: 100%;
  width: 100%;
  background: inherit;
  border: none;
  border-radius: 0;
  padding: 0;
`;

const VoteButtonContent = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
  width: 100%;
`;

const VoteButtonText = styled.span`
  font-size: 6px;
`;
