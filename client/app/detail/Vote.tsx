"use client";

import { FaRegThumbsDown, FaRegThumbsUp } from "react-icons/fa";
import styled from "styled-components";

import { StyledCard } from "./Card";

export const Vote = () => {
  return (
    <StyledVote>
      <h3 className="sub-title">그만 써도 될까요?</h3>
      <StyledCard className="vote-box">
        <div className="vote">
          <div className="text">네!</div>
          <FaRegThumbsUp className="icon" />
        </div>
        <div className="vote">
          <FaRegThumbsDown className="icon" />
          <div className="text">안돼요</div>
        </div>
      </StyledCard>
    </StyledVote>
  );
};

const StyledVote = styled.div`
  .sub-title {
    margin: 0 24px;
  }
  .vote-box {
    display: flex;
    flex-direction: row;
    .vote {
      cursor: pointer;
      display: flex;
      flex-direction: row;
      margin: 0 12px;
      .text {
        margin: 0 8px;
      }
      .icon {
        border: 50%;
        /* padding: 12px; */
        /* border: solid; */
      }
    }
  }
`;
