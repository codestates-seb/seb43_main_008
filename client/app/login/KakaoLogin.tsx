"use client";
import Image from "next/image";
import styled from "styled-components";

export default function KakaoLogin() {
  const kakaoLogin = () => {
    window.location.href = `${process.env.NEXT_PUBLIC_API_URL}/login/kakao`;
  };
  return (
    <KakaoLoginButton onClick={() => kakaoLogin()}>
      <KakaoLogoBox>
        <Image
          src="/socialLoginButton/kakaoLogo.png"
          alt="카카오 로고"
          width="24"
          height="24"
        />
      </KakaoLogoBox>
      <KakaoLoginButtonContext>카카오로 시작하기</KakaoLoginButtonContext>
    </KakaoLoginButton>
  );
}

const KakaoLoginButton = styled.button`
  display: flex;
  width: 300px;
  height: 50px;
  background-color: #fee500;
  border: none;
  box-shadow: 0 0 0.25em rgba(67, 71, 85, 0.27),
    2px 0.25em 24px 0 rgba(90, 125, 188, 0.05);
`;

const KakaoLogoBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 20%;
  height: 100%;
`;

const KakaoLoginButtonContext = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  font-size: 16px;
`;
