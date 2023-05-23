"use client";
import { useParams } from 'next/navigation';
import { useEffect, useState } from "react";
import { FaRegThumbsDown, FaRegThumbsUp } from "react-icons/fa";
import styled from "styled-components";

// FaThumbsDown, FaThumbsUp 
import { GetVote, PutVote } from "../api/voteApi"
import { StyledCard } from "./Card";


export const Vote = () => {
  const [voted, setVoted] = useState<any>(null)
  const series = useParams();

  useEffect(() => {
    GetVote(series.id).then((data) => {
      if (data) {
        setVoted(data)
      }
    })
  }, [series.id]);

  const handleVoting = async (voting: string) => {
    await PutVote(series.id, voting)
    GetVote(series.id).then((data) => {
      if (data) {
        setVoted(data)
      }
    })
  }

  return (
    <StyledVote>
      <h3 className="sub-title">그만 써도 될까요?</h3>
      <StyledCard style={{ alignItems: "center" }}>
        <div className="vote-box">
          <div className="vote" onClick={() => handleVoting("1")}>
            {voted?.isVotedMember || voted?.voteStatus === "SERIES_QUIT" ? <div className="sub-text yes">{voted.voteCount === 1 ? voted.voteAgree : voted.revoteAgree}</div> : null}
            <div className="text">네!</div>
            <FaRegThumbsUp className="icon" />
          </div>
          <div className="vote" onClick={() => handleVoting("0")}>
            <FaRegThumbsDown className="icon" />
            <div className="text">안돼요</div>
            {voted?.isVotedMember || voted?.voteStatus === "SERIES_QUIT" ? <div className="sub-text no">{voted.voteCount === 1 ? voted.voteDisagree : voted.revoteDisagree}</div> : null}
          </div>
        </div>
        {voted?.isVotedMember || voted?.voteStatus === "SERIES_QUIT" ? (
          <div className="sub-text message">
            더 이상의 투표는 불가능해요.
          </div>
        ) : (
          <div className="sub-text message">
            투표에 참여하면 결과를 확인할 수 있습니다
          </div>
        )}
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
