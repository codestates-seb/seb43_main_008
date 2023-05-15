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

  display: flex;
  align-items: center;

  .title {
    margin-left: 1rem;
    font-size: 1.65rem;
    font-weight: 900;
  }
`;
