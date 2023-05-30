"use client";

import { useParams, useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import styled from "styled-components";

import { GetVoteResult, VoteEnd, VoteNoEnd } from "../api/voteResult";

export default function VoteResultContext() {
  const [agree, setAgree] = useState(0);
  const [disAgree, setDisAgree] = useState(0);
  const params = useParams();
  const router = useRouter();

  useEffect(() => {
    GetVoteResult(params.id).then((data) => {
      setAgree(data.data.voteAgree);
      setDisAgree(data.data.voteDisagree);
    });
  }, []);

  const onClickVoteNoEnd = async () => {
    try {
      await VoteNoEnd(params.id);
      localStorage.setItem("plastic", params.id);
      router.push("/my-list");
    } catch (error) {
      console.error(error);
      throw error;
    }
  };

  const onClickVoteEnd = async () => {
    try {
      await VoteEnd(params.id);
      router.push("/my-page");
    } catch (error) {
      console.error(error);
      throw error;
    }
  };

  return (
    <ResultContextContainer>
      <ResultContext>이전 투표 결과는</ResultContext>
      <ResultContext>{`찬성 ${agree}표 반대 ${disAgree}표 입니다 :)`}</ResultContext>

      <ButtonBox>
        <EndButton onClick={onClickVoteNoEnd}>조금 더 써볼까요?</EndButton>
        <NoEndButton onClick={onClickVoteEnd}>
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
