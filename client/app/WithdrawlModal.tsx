"use client";

import Image from "next/image";
import styled from "styled-components";

import { WithdrawlMember } from "./api/memberEdit";

interface WithdrawlModalProps {
  isWithdrawlModalOpen: boolean;
  setIsWithdrawlModalOpen: (value: boolean) => void;
}

export default function WithdrawlModal({
  isWithdrawlModalOpen,
  setIsWithdrawlModalOpen,
}: WithdrawlModalProps) {
  const handleClose = () => {
    setIsWithdrawlModalOpen(false);
  };

  return (
    isWithdrawlModalOpen && (
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
          <p>정말로 탈퇴하시나요?</p>
          <ConfirmButton onClick={WithdrawlMember}>확인</ConfirmButton>
          <ConfirmButton onClick={handleClose}>취소</ConfirmButton>
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
  margin-top: 10px;
  cursor: pointer;
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
