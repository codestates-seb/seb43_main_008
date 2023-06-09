"use client";

// import axios from "axios";
import Image from "next/image";
// import { signIn, useSession } from "next-auth/react";
import styled from "styled-components";

export default function GoogleLogin() {
  // const { data: session, status } = useSession();

  const googleLogin = () => {
    window.location.href = `${process.env.NEXT_PUBLIC_API_URL}/login/google`;
  };

  return (
    <GoogleLoginButton onClick={() => googleLogin()}>
      <GoogleLogoBox>
        <Image
          src="/socialLoginButton/googleLogo.png"
          alt="구글 로고"
          width="24"
          height="24"
        />
      </GoogleLogoBox>
      <GoogleLoginButtonContext>구글로 시작하기</GoogleLoginButtonContext>
    </GoogleLoginButton>
  );
}

const GoogleLoginButton = styled.button`
  display: flex;
  width: 300px;
  height: 50px;
  background-color: #ffffff;
  border: none;
  box-shadow: 0 0 0.25em rgba(67, 71, 85, 0.27),
    2px 0.25em 24px 0 rgba(90, 125, 188, 0.05);
`;

const GoogleLogoBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 20%;
  height: 100%;
`;

const GoogleLoginButtonContext = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  font-size: 16px;
`;
