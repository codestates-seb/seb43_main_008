"use client";

import React, { useState } from "react";
import styled from "styled-components";

const InformationModal = styled.div`
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

const CloseButton = styled.button`
  position: absolute;
  right: 10px;
  top: 7px;
  background: transparent;
  border: none;
  font-size: 1.2em;
  color: #9b9ba0;
`;

const ConfirmButton = styled.button`
  color: ${(props) =>
    props.disabled ? " #dadada " : "#222"}; // 활성화 상태에 따라 색 변경
  background-color: #fcfcfd;
  border: 1px solid rgba(34, 36, 38, 0.5);
  padding: 0.3rem 0.5rem;
  margin-left: 110px;
  border-radius: 3px;
  margin-top: 15px;
  cursor: pointer;
`;

// const Message = styled.p`
//   margin-bottom: 10px;
// `;

interface InformationProps {
  message: string;
  additionalMessage?: string;
  onClose: () => void; // '확인' 버튼을 눌렀을 때 호출될 함수를 prop으로 추가
}

const Information: React.FC<InformationProps> = ({
  message,
  additionalMessage,
  onClose,
}) => {
  const [checkbox1, setCheckbox1] = useState(false); // 체크박스1의 상태
  const [checkbox2, setCheckbox2] = useState(false); // 체크박스2의 상태

  const canSubmit = checkbox1 && checkbox2; // 두 체크박스가 모두 체크되었는지 판단

  return (
    <InformationModal onClick={onClose}>
      <ModalContent onClick={(e) => e.stopPropagation()}>
        {/* <p
          style={{
            fontSize: "14px",
            marginLeft: "28px",
          }}
        >
          {message}
        </p> */}
        <p
          style={{ fontSize: "14px", marginLeft: "28px", marginBottom: "10px" }}
        >
          {message}
        </p>
        {additionalMessage && (
          <p
            style={{
              fontSize: "12px",
              marginLeft: "12px",
              marginBottom: "5px",
            }}
          >
            {additionalMessage}
          </p>
        )}

        <div>
          <input
            type="checkbox"
            id="checkbox1"
            checked={checkbox1}
            onChange={() => setCheckbox1(!checkbox1)}
            style={{
              fontSize: "11px",
              marginTop: "30px",
              marginLeft: "8px",
            }}
          />
          <label
            htmlFor="checkbox1"
            style={{
              fontSize: "13px",
              marginTop: "30px",
              marginLeft: "3px",
            }}
          >
            {" "}
            안내 내용을 확인하였습니다.
          </label>
        </div>
        <div>
          <input
            type="checkbox"
            id="checkbox2"
            checked={checkbox2}
            onChange={() => setCheckbox2(!checkbox2)}
            style={{
              fontSize: "11px",
              marginTop: "10px",
              marginLeft: "8px",
            }}
          />
          <label
            htmlFor="checkbox2"
            style={{
              fontSize: "13px",
              marginTop: "30px",
              marginLeft: "3px",
            }}
          >
            {" "}
            안내에 동의합니다.
          </label>
        </div>
        {!canSubmit && (
          <p
            style={{
              fontSize: "11px",
              marginTop: "30px",
              marginLeft: "8px",
            }}
          >
            체크 박스 두개 다 체크해야 확인 버튼을 누를 수 있습니다!
          </p>
        )}
        <CloseButton onClick={onClose}>×</CloseButton>
        <ConfirmButton
          disabled={!canSubmit}
          onClick={canSubmit ? onClose : null}
        >
          확인
        </ConfirmButton>
        {/* '확인' 버튼을 추가하고, 두 체크박스가 모두 체크되었을 때만 클릭 가능 */}
      </ModalContent>
    </InformationModal>
  );
};

export default Information;
