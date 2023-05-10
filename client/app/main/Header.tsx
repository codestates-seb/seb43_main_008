"use client";

import styled from "styled-components";

export const Header = () => {
  return (
    <StyledHeader>
      <h1 className="title">홈</h1>
    </StyledHeader>
  );
};

const StyledHeader = styled.header`
  height: 67.227px;
  margin: 0 12px;

  display: flex;
  align-items: center;

  /* position: fixed;
  top: 0;

  background-color: white;
  width: 100vw; */
  .title {
    margin-left: 1rem;
    font-size: 1.65rem;
    font-weight: 900;
  }
`;
