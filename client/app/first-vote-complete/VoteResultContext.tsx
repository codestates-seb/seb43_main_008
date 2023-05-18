"use client";

import styled from "styled-components";

export default function VoteResultContext() {
  const MockData = "51 대 49";
  return (
    <ResultContextContainer>
      <p>{`이전 투표 결과는 ${MockData}`}</p>

      <ButtonBox>
        <Button>조금 더 써볼까요?</Button>
        <Button>종료할게요(플라스틱 졸업시키기)</Button>
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
