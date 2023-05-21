import styled from "styled-components";

interface VoteModalProps {
  isVoteModalOpen: boolean;
  setIsVoteModalOpen: (value: boolean) => void;
  isVotePublic: boolean;
  setIsVotePublic: (value: boolean) => void;
}

export default function VoteModal({
  isVoteModalOpen,
  setIsVoteModalOpen,
  isVotePublic,
  setIsVotePublic,
}: VoteModalProps) {
  const handleConfirm = () => {
    setIsVoteModalOpen(false);
  };

  return (
    isVoteModalOpen && (
      <Modal>
        <ModalContent>
          {/* <CloseButton onClick={onClose}>×</CloseButton> */}
          <p
            style={{
              marginBottom: "4px",
              marginTop: "10px",
              marginLeft: "30px",
              fontSize: "13px",
            }}
          />
          투표를 신청하시면,
          <p
            style={{
              marginBottom: "4px",
              marginTop: "10px",
              marginLeft: "13px",
              fontSize: "16px",
            }}
          >
            더 이상 시리즈를 작성하실 수 없어요~
          </p>
          <ConfirmButton onClick={handleConfirm}>확인</ConfirmButton>
        </ModalContent>
      </Modal>
    )
  );
}

const Modal = styled.div`
  position: fixed;
  z-index: 1;
  right: 48px;
  top: 70px;
  overflow: none;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
`;

const ModalContent = styled.div`
  background-color: #fefefe;
  padding: 10px;
  border: 1px solid #9b9ba0;
  width: 280px;
  height: 225px;
  border-radius: 10px;
  gap: 5px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
`;

const ConfirmButton = styled.button`
  background-color: #fcfcfd;
  color: #222;
  border: 1px solid rgba(34, 36, 38, 0.5);
  padding: 0.3rem 0.5rem;
  border-radius: 3px;
  margin-top: 15px;
  cursor: pointer;
`;
// const CloseButton = styled.button`
//   position: absolute;
//   right: 10px;
//   top: 7px;
//   background: transparent;
//   border: none;
//   font-size: 1.2em;
//   color: #9b9ba0;
// `;
