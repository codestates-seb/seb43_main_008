"use client";
import Image from "next/image";
import styled from "styled-components";

export default function NaverLogin() {
  const naverLogin = () => {
    window.location.href = `${process.env.NEXT_PUBLIC_API_URL}/login/naver`;
  };
  return (
    <NaverLoginButton onClick={() => naverLogin()}>
      <NaverLogoBox>
        <Image
          src="/socialLoginButton/naverLogo.png"
          alt="네이버 로고"
          width="40"
          height="40"
        />
      </NaverLogoBox>
      <NaverLoginButtonContext>네이버로 시작하기</NaverLoginButtonContext>
    </NaverLoginButton>
  );
}

const NaverLoginButton = styled.button`
  display: flex;
  width: 300px;
  height: 50px;
  background-color: #03c75a;
  border: none;
  box-shadow: 0 0 0.25em rgba(67, 71, 85, 0.27),
    2px 0.25em 24px 0 rgba(90, 125, 188, 0.05);
`;

const NaverLogoBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 20%;
  height: 100%;
`;

const NaverLoginButtonContext = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  font-size: 16px;
  color: #ffffff;
`;
