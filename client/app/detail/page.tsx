"use client";

import styled from "styled-components";
import { Header } from "./Header";
import { StyledCard } from "./Card";
import { Slider } from "./Slider";
import { FaRegThumbsUp, FaRegThumbsDown } from "react-icons/fa";

export default function Detail() {
  return (
    <StyledDetail>
      <Header />
      <Slider />
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

      <h3 className="sub-title">댓글 달기</h3>
      <StyledCard className="comment">
        <h3 className="sub-title">댓글 달기</h3>
      </StyledCard>
    </StyledDetail>
  );
}

const StyledDetail = styled.div`
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
