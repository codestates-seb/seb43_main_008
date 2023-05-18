"use client";

import styled from "styled-components";

import GoogleLoginButton from "./GoogleLogin";
import KakaoLoginButton from "./KakaoLogin";
import NaverLoginButton from "./NaverLogin";

export default function SocialLoginButtonBox() {
  return (
    <SocialLoginButtonContainer>
      <GoogleLoginButton />
      <KakaoLoginButton />
      <NaverLoginButton />
    </SocialLoginButtonContainer>
  );
}

const SocialLoginButtonContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  height: 180px;
  margin-bottom: 120px;
`;
