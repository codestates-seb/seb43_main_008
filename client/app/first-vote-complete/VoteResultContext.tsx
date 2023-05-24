"use client";

import { useParams } from "next/navigation";
import { useEffect, useState } from "react";
import styled from "styled-components";

import { GetVoteResult, VoteEnd, VoteNoEnd } from "../api/voteResult";

export default function VoteResultContext() {
  const [agree, setAgree] = useState(0);
  const [disAgree, setDisAgree] = useState(0);
  const params = useParams();

  useEffect(() => {
    GetVoteResult(params.id).then((data) => {
      setAgree(data.data.voteAgree);
      setDisAgree(data.data.voteDisagree);
    });
  }, []);

  return (
    <ResultContextContainer>
      <ResultContext>{`이전 투표 결과는 ${agree} 대 ${disAgree}`}</ResultContext>

      <ButtonBox>
        <EndButton onClick={() => VoteNoEnd(params.id)}>
          조금 더 써볼까요?
        </EndButton>
        <NoEndButton onClick={() => VoteEnd(params.id)}>
          종료할게요(플라스틱 졸업시키기)
        </NoEndButton>
      </ButtonBox>
    </ResultContextContainer>
  );
}

const ResultContextContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: calc(100% - 85px);
`;

const ResultContext = styled.p`
  font-size: 25px;
`;

const ButtonBox = styled.div`
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
`;

const NoEndButton = styled.button`
  width: 100%;
  height: 60px;
  margin: 10px 0px;
  font-size: 20px;
  color: #85858e;
  background-color: #f5f2f0;
  border-radius: 16px;
  border: solid 1px #85858e;
`;

const EndButton = styled.button`
  width: 100%;
  height: 60px;
  border-radius: 16px;
  margin: 10px 0px;
  font-size: 20px;
  border: solid 1px #3f910c;
  background-color: #eff4e7;
  color: #3f910c;
`;
