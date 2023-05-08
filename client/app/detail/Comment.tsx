"use client";

import styled from "styled-components";

import { StyledCard } from "./Card";

export const Comment = () => {
  return (
    <StyledComment>
      <h3 className="sub-title">댓글 달기</h3>
      <StyledCard className="comment">
        <h3 className="sub-title">댓글 달기</h3>
      </StyledCard>
    </StyledComment>
  );
};

const StyledComment = styled.div`
  .sub-title {
    margin: 0 24px;
  }
`;
