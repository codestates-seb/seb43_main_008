import styled from "styled-components";

const Modal = styled.div`
  position: fixed;
  z-index: 1;
  right: 48px;
  top: 70px;
  overflow: none;
`;

const ModalContent = styled.div`
  background-color: #fefefe;
  padding: 10px;
  border: 1px solid #9b9ba0;
  width: 280px;
  height: 225px;
  border-radius: 10px;
`;

const RadioGroup = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 25px;
`;

const RadioLabel = styled.label`
  display: flex;
  align-items: center;
  cursor: pointer;
  font-size: 12px;
  line-height: 20px;
  color: #757575;
  margin-top: 10px;
`;

const RadioInput = styled.input`
  margin-right: 8px;
`;

const ConfirmationMessage = styled.p`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 15px;
  font-size: 12px;
  line-height: 1.4;
`;

const ConfirmButton = styled.button`
  background-color: #fcfcfd;
  color: #222;
  border: 1px solid rgba(34, 36, 38, 0.5);
  padding: 0.3rem 0.5rem;
  margin-left: 110px;
  border-radius: 3px;
  margin-top: 15px;
  cursor: pointer;
`;

// export default function HeaderModal({
//   isModalOpen,
//   setIsModalOpen,
//   isPublic,
//   setIsPublic,
// }) {
//   const handleConfirm = () => {
//     // 여기에 라디오 버튼을 선택 -> 확인 버튼을 눌렀을 때, 이 설정을 어딘가에 저장하는 로직을 추가 가능
//     setIsModalOpen(false);
//   };

interface HeaderModalProps {
  isModalOpen: boolean;
  setIsModalOpen: (value: boolean) => void;
  isPublic: boolean;
  setIsPublic: (value: boolean) => void;
}

export default function HeaderModal({
  isModalOpen,
  setIsModalOpen,
  isPublic,
  setIsPublic,
}: HeaderModalProps) {
  return (
    isModalOpen && (
      <Modal>
        <ModalContent>
          <p
            style={{
              marginBottom: "4px",
              marginTop: "10px",
              marginLeft: "13px",
              fontSize: "13px",
            }}
          >
            작성한 시리즈의 공개 여부를 선택해 주세요❤️
          </p>
          <RadioGroup>
            <RadioLabel>
              <RadioInput
                type="radio"
                value="public"
                checked={isPublic}
                onChange={() => setIsPublic(true)}
              />
              공개
            </RadioLabel>
            <RadioLabel>
              <RadioInput
                type="radio"
                value="private"
                checked={!isPublic}
                onChange={() => setIsPublic(false)}
              />
              비공개
            </RadioLabel>
          </RadioGroup>
          <ConfirmationMessage>
            {isPublic ? "공개로 설정하였습니다!" : "비공개로 설정하였습니다!"}
          </ConfirmationMessage>
          <ConfirmButton>확인</ConfirmButton>
        </ModalContent>
      </Modal>
    )
  );
}
