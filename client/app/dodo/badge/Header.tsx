"use client";

import Image from "next/image";
import Link from "next/link";
import React, { useState } from "react";
import { AiOutlineLeft } from "react-icons/ai";
import styled from "styled-components";

export default function Header({
  backButton,
  textContent,
  mainButton,
}: {
  backButton: boolean;
  textContent: string | null;
  mainButton: boolean;
}) {
  // 홈버튼 호버설정
  const [isHovered, setIsHovered] = useState(false);

  return (
    <HeaderContainer>
      {backButton ? (
        <BackArrowContainer>
          <AiOutlineLeft size="24" />
        </BackArrowContainer>
      ) : (
        <BackArrowContainer />
      )}
      {textContent ? <HeaderText>{textContent}</HeaderText> : null}
      {mainButton ? (
        <MainButtonContainer>
          <Link href="/">
            <MainButton
              onMouseEnter={() => setIsHovered(true)}
              onMouseLeave={() => setIsHovered(false)}
            >
              <Image
                src={isHovered ? "/icons/HomeFill.svg" : "/homeGrey.svg"}
                alt="홈 버튼"
                width="50"
                height="50"
              />
            </MainButton>
          </Link>
        </MainButtonContainer>
      ) : (
        <MainButtonContainer />
      )}
    </HeaderContainer>
  );
}

const HeaderContainer = styled.header`
  position: sticky;
  top: 0;
  display: flex;
  justify-content: space-between;
  height: 67px;
  background-color: #ffffff;
  z-index: 1;
`;

const BackArrowContainer = styled.div`
  display: flex;
  align-items: center;
  height: 70px;
  width: 44px;
  padding: 10px;
`;

const HeaderText = styled.span`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  font-size: 20px;
`;

const MainButtonContainer = styled.div`
  display: flex;
  align-items: center;
  height: 70px;
  width: 44px;
  padding: 10px 10px 10px 0;
`;

const MainButton = styled.button`
  /* height: 100%;
  width: 100%;
  font-size: 13px;
  font-weight: 700; */
  background: inherit;
  border: none;
  border-radius: 0;
  padding: 0;
`;
