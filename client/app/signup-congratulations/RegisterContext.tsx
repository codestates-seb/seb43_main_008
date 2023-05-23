"use client";

import Image from "next/image";
import styled from "styled-components";

export default function RegisterContext() {
  return (
    <RegisterContextContainer>
      <span>쓰고 또 쓰고 또 쓰는 플랫폼</span>
      <span>플라스틱 재활용 커뮤니티</span>
      <br />
      <span>쓰쓰또쓰에 오신 것을</span>
      <span>환영합니다.</span>
      <Image
        src="/images/signup-congratulations.png"
        alt="재활용 이미지"
        width="250"
        height="300"
      />
    </RegisterContextContainer>
  );
}

const RegisterContextContainer = styled.div`
  height: 70vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 50px;
  padding: 0 15px;
  font-size: 20px;
  font-weight: bold;
  color: #2c2c2c;
  line-height: 1.5;
  span {
    margin-bottom: 10px;
  }
`;
