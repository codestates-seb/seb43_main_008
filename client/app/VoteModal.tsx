// import axios from "axios";
import Image from "next/image";
import { useRouter } from "next/navigation";
import styled from "styled-components";

import { PostVote } from "./api/voteApi";

interface VoteModalProps {
  isVoteModalOpen: boolean;
  setIsVoteModalOpen: (value: boolean) => void;
}

export default function VoteModal({
  isVoteModalOpen,
  setIsVoteModalOpen,
}: VoteModalProps) {
  const router = useRouter();
  const seriesId =
    typeof window !== "undefined" ? localStorage.getItem("plastic") : null;

  const modalClick = async (seriesId: string) => {
    try {
      await PostVote(seriesId);
      router.push(`/detail/${seriesId}`);
    } catch (error) {
      throw error;
    }
  };

  const handleClose = () => {
    setIsVoteModalOpen(false);
  };

  return (
    isVoteModalOpen && (
      <Modal>
        <ModalContent>
          <CloseButton>
            <Image
              src={"/close.svg"}
              width={13}
              height={13}
              alt={"취소버튼"}
              onClick={handleClose}
            />
          </CloseButton>
          <p
            style={{
              marginBottom: "3px",
              marginLeft: "10px",
              fontSize: "15px",
              color: "#222",
            }}
          >
            이 시리즈를 투표에 올리시면,
          </p>
          <p
            style={{
              marginBottom: "4px",
              marginTop: "10px",
              marginLeft: "13px",
              fontSize: "15px",
              color: "#222",
            }}
          >
            더 이상 이 시리즈를 작성하실 수 없어요.
          </p>
          <ConfirmButton onClick={() => modalClick(seriesId)}>
            확인
          </ConfirmButton>
        </ModalContent>
      </Modal>
    )
  );
}

const Modal = styled.div`
  position: fixed;
  z-index: 1;
  right: 48px;
  top: 190px;
  overflow: none;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
`;

const ModalContent = styled.div`
  background-color: #fefefe;
  padding: 10px;
  border: 1px solid #3f910c;
  width: 280px;
  height: 200px;
  border-radius: 10px;
  gap: 5px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
`;

const ConfirmButton = styled.button`
  height: 33px;
  width: 80px;
  background-color: #f5f2f0;
  border: 1px #85858e solid;
  border-radius: 16px;
  margin-top: 25px;
  cursor: pointer;

  &:hover {
    border: solid 1px #3f910c;
    background-color: #eff4e7;
    color: #3f910c;
  }

  &:active {
    transform: translateY(-1px);
  }
`;

const CloseButton = styled.button`
  position: absolute;
  right: 10px;
  top: 7px;
  background: transparent;
  border: none;
  font-size: 1.2em;
  color: #9b9ba0;
`;
