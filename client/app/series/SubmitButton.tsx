"use client";
import { useState } from "react";
import styled from "styled-components";

import Information from "./WarningInformationModal";

interface SubmitButtonProps {
  onCreate?: () => void;
  onClose?: () => void;
}

export default function SubmitButton({ onCreate, onClose }: SubmitButtonProps) {
  const [showInformation, setShowInformation] = useState(false);
  // const [isWritten, setIsWritten] = useState(false);

  // photo와 text라는 두 개의 상태가 추가 -> 각각 사진과 텍스트의 작성 상태를 나타냄
  // 버튼을 클릭하면 photo와 text 상태가 모두 true인지를 확인하고, 그 결과에 따라 isWritten 상태를 업데이트

  // const [photo, setPhoto] = useState(false);
  // const [text, setText] = useState(false);

  const handleCreated = () => {
    // if (photo && text) {
    //   setIsWritten(true);
    // } else {
    //   setIsWritten(false);
    // }

    setShowInformation(true);
    if (onCreate) {
      onCreate();
    }
  };
  const handleCloseModal = () => {
    setShowInformation(false);
    if (onClose) {
      onClose();
    }
  };

  // const handlePhotoWritten = () => {
  //   setPhoto(true);
  // };

  // const handleTextWritten = () => {
  //   setText(true);
  // };

  return (
    <>
      <SubmitButtonStyled
        type="submit"
        onClick={handleCreated}
        // isWritten={isWritten}
      >
        작성 완료
      </SubmitButtonStyled>
      {showInformation && ( // showInformation 상태에 따라 Information 컴포넌트를 렌더링
        <Information
          message="아래의 안내 내용을 확인해주세요❤️"
          additionalMessage="안내 내용 좀 우리 모두 같이 정해보아용~ 데헷데헷"
          onClose={handleCloseModal} // 확인 버튼을 누르면 호출될 함수를 prop으로 전달
        />
      )}
    </>
  );
}
// interface SubmitButtonStyledProps {
//   isWritten: boolean;
// }

// const SubmitButtonStyled = styled.button<SubmitButtonStyledProps>`
const SubmitButtonStyled = styled.button`
  background-color: #fcfcfd;
  color: #222;
  border: 1px solid rgba(34, 36, 38, 0.5);
  padding: 0.6rem 0.6rem;
  border-radius: 3px;
  cursor: pointer;
  margin-top: 10px;
  /* position: fixed; */
  min-width: 325px;

  &:active {
    transform: translateY(-2px);
  }
`;

//-> 주석처리한 부분(사진과 글이 작성되어야 작성 완료 버튼이 활성화되는 기능) 사용하려면 사진과 텍스트 작성 방법에 따라 달라짐으로 후에 다시 구현
// SubmitButtonStyled에 넣기 <- color: ${({ isWritten }) => (isWritten ? "#222" : "#dadada")};
