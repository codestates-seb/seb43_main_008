"use client";

import styled from "styled-components";

interface ConfirmButtonProps {
  disabled: boolean;
  onClick: (() => void) | null;
  handleCreated: (() => void) | null;
}

export default function ConfirmButtonComponent({
  disabled,
  onClick,
  handleCreated,
}: ConfirmButtonProps) {
  return (
    <ConfirmButton
      disabled={disabled}
      onClick={() => {
        if (onClick) onClick();
        if (handleCreated) handleCreated();
      }}
    >
      확인
    </ConfirmButton>
  );
}
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
