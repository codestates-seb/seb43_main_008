"use client";

import Link from "next/link";
import styled from "styled-components";

export default function HomeButton() {
  return (
    <HomeButtonContainer>
      <Link href="/main" passHref legacyBehavior>
        <HomeNavButton>홈으로</HomeNavButton>
      </Link>
    </HomeButtonContainer>
  );
}

const HomeButtonContainer = styled.div`
  position: absolute;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  max-width: 1024px;
  width: 100%;
  height: 60px;
  padding: 10px 15px;
`;

const HomeNavButton = styled.button`
  height: 100%;
  width: 100%;
  border: none;
  border-radius: 4px;
  background-color: white;
  color: black;
  border: 1px solid black;
  border-radius: 4px;
`;
