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
      <p>{`이전 투표 결과는 ${agree} 대 ${disAgree}`}</p>

      <ButtonBox>
        <Button onClick={() => VoteNoEnd(params.id)}>조금 더 써볼까요?</Button>
        <Button onClick={() => VoteEnd(params.id)}>
          종료할게요(플라스틱 졸업시키기)
        </Button>
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
  height: 100%;
`;

const ButtonBox = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const Button = styled.button`
  width: 100%;
  height: 40px;
  border-radius: 5px;
  border: 1px solid #000000;
  background-color: #ffffff;
  margin: 10px 0px;
`;
