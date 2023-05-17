"use client";

import styled from 'styled-components';

export const Medal = () => {
  return (
    <StyledMedal>
      🏅
    </StyledMedal>
  )
}

const StyledMedal = styled.div`
  z-index: 10;
  
  position: absolute;
  right: -10%;
  transform: translate(0%, 220%);

  width: calc(25vw / 3);
  height: calc(25vw / 3);

  display: flex;
  justify-content: center;
  align-items: center;

  background-color:rgba(255, 255, 255, 0.65);
  border-radius: 50%;
  box-shadow: rgba(149, 157, 165, 0.5) 8px 8px 12px;
  border: solid white 1.5px;
  font-size: 5vw;
  /* font-size: 14vw; */

  @media screen and (min-width: 1024px) {
      width: 85px;
      height: 85px;
      font-size: 51px;
  }
`