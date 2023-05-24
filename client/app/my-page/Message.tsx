"use client";

import styled from "styled-components";

export const Message = () => {
  return (
    <StyledMessage>
      <div className='text'>아직은 볼 수 없는 게시글이에요.</div>
    </StyledMessage>
  )
}

const StyledMessage = styled.div`
  width: 80vw;
  height: fit-content;
  padding: 4rem;

  background-color: white;
  border-radius: 8px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;

  position: fixed;
  bottom: -100px;
  left: 50%;
  transform: translateX(-50%);
  animation: slide-up 3s ease-in-out;

  @keyframes slide-up {
    0% {
      opacity: 0;
      transform: translate(-50%, 100%);
    }
    10% {
      opacity: 1;
    }
    90% {
      opacity: 1;
    }
    100% {
      opacity: 0;
      transform: translate(-50%, -100%);
    }
  }
`