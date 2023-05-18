"use client";
import styled from "styled-components";

export default function LoginContext() {
  return (
    <LoginContextBox>
      <p>쓰쓰또쓰의 더 많은 서비스를 </p>
      <p>사용하고 싶다면 로그인 해주세요</p>
    </LoginContextBox>
  );
}

const LoginContextBox = styled.div`
  margin-top: 20px;
  margin-bottom: 10px;

  p {
    font-size: 20px;
    color: #666;
    text-align: center;
    margin-bottom: 10px;
  }
`;
