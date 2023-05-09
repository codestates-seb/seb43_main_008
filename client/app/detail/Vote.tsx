"use client";

import { FaRegThumbsDown, FaRegThumbsUp } from "react-icons/fa";
import styled from "styled-components";
import { useState, useEffect } from "react";
import { StyledCard } from "./Card";

// React.FC는 props가 없음을 명시해준다.
export const Vote: React.FC = () => {
  const [voted, setVoted] = useState<number | undefined>();
  console.log(voted);

  // 언마운트 됐을 때 서버에 투표 결과를 보낸다.
  // selected 값이 있을 때만 보낸다.
  useEffect(() => {
    return () => {
      if (voted) {
        console.log(voted);
      }
    };
  }, []);

  // 투표 아예 취소 가능한가?
  const yesHandler = () => {
    setVoted(1);
    if (voted === 1) setVoted(undefined);
  };
  const noHandler = () => {
    setVoted(0);
    if (voted === 0) setVoted(undefined);
  };

  return (
    <StyledVote>
      <h3 className="sub-title">그만 써도 될까요?</h3>
      <StyledCard style={{ alignItems: "center" }}>
        <div className="vote-box">
          <div className="vote" onClick={() => yesHandler()}>
            {voted ? <div className="sub-text yes">200표</div> : null}
            <div className="text">네!</div>
            <FaRegThumbsUp className="icon" />
          </div>
          <div className="vote" onClick={() => noHandler()}>
            <FaRegThumbsDown className="icon" />
            <div className="text">안돼요</div>
            {voted ? <div className="sub-text no">100표</div> : null}
          </div>
        </div>
        {!voted ? (
          <div className="sub-text message">
            투표에 참여하면 결과를 확인할 수 있습니다
          </div>
        ) : null}
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
    justify-content: center;
    .vote {
      cursor: pointer;

      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: baseline; // 수정 필요
      margin: 0 12px;

      .text {
        margin: 0 8px;
      }
    }
  }
`;
