"use client";
import styled from "styled-components";

export default function RegisterContext() {
  return (
    <RegisterContextContainer>
      <span>쓰고 또 쓰고 또 쓰는 플랫폼</span>
      <span>플라스틱 재활용 커뮤니티</span>
      <br />
      <span>쓰쓰또쓰에 오신 것을</span>
      <span>환영합니다.</span>
      <ImageContainer />
    </RegisterContextContainer>
  );
}

const RegisterContextContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
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

const ImageContainer = styled.div`
  width: 250px;
  height: 300px;
  background-color: #2c2c2c;
`;
