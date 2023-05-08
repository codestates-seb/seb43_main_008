"use client";

import "./header.module.css";

import React from "react";
import { AiFillCaretLeft } from "react-icons/ai";
import styled from "styled-components";

const LayoutContainer = styled.div`
  /* max-width: 1024px; */
  /* padding: 15px; */
  box-sizing: border-box;
  margin: 0 auto;
  @media (max-width: 1024px) {
    flex-direction: column;
    align-items: flex-start;
  }
`;

const HeaderContainer = styled.header`
  display: flex;
  justify-content: center;
  padding: 15px;
  margin: 0 auto;
  background-color: #cfeedc;
  border-bottom: 2px solid #54b435;
`;

const BackButton = styled.span`
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: transparent;
  border: none;
  transition: transform 0.2s ease-in-out;

  &:hover {
    transform: translateY(-1px);
  }
`;

// 쓰또 로고 -> 가운데 정렬
const Logo = styled.span`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 1rem;
`;

export default function page() {
  return (
    <>
      <LayoutContainer>
        <HeaderContainer>
          <BackButton>
            <button
              style={{
                background: "transparent",
                border: "none",
              }}
            >
              <AiFillCaretLeft
                style={{
                  fontSize: "25px",
                  color: "#222",
                  cursor: "pointer",
                }}
              />
            </button>
          </BackButton>
          <Logo>
            <img src="/mainLogo.svg" alt="쓰또로고" />
          </Logo>
          {/* <Navigation></Navigation> */}
        </HeaderContainer>
      </LayoutContainer>
    </>
  );
}
