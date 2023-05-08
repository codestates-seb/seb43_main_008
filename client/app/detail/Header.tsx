"use client";

import styled from "styled-components";
import { AiOutlineLeft } from "react-icons/ai";

export const Header = () => {
  return (
    <StyledHeader>
      <AiOutlineLeft size="21" className="icon" />
      <div className="title">봉다리 세바스찬</div>
      <div className=""></div>
    </StyledHeader>
  );
};

const StyledHeader = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;

  height: 67.227px;
  margin: 0 12px;

  font-size: 18px;
  .title {
    margin-right: 21px;
  }
`;