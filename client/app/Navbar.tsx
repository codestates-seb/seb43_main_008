"use client";

import styled from "styled-components";
import { useState } from "react";
import { AiFillHome, AiOutlineHome } from "react-icons/ai";
import { BsHeartFill, BsBookmarkFill, BsPersonFill } from "react-icons/bs";
import { BsHeart, BsBookmark, BsPerson } from "react-icons/bs";

export default function Navbar() {
  const [selected, setSelected] = useState("홈");

  const handleMenuClick = (menuName: string) => {
    setSelected(menuName);
  };

  return (
    <StyledNavbar>
      <div className="container">
        <div
          className={`home menu ${selected === "홈" ? "selected" : ""}`}
          onClick={() => handleMenuClick("홈")}
        >
          {selected === "홈" ? (
            <AiFillHome className="icon" />
          ) : (
            <AiOutlineHome className="icon" />
          )}
          <div className="text">홈</div>
        </div>
        <div
          className={`follow menu ${selected === "팔로우" ? "selected" : ""}`}
          onClick={() => handleMenuClick("팔로우")}
        >
          {selected === "팔로우" ? (
            <BsHeartFill className="icon" />
          ) : (
            <BsHeart className="icon" />
          )}
          <div className="text">팔로우</div>
        </div>
        <div className="plus-button">+</div>
        <div
          className={`add-series menu ${
            selected === "시리즈 작성" ? "selected" : ""
          }`}
          onClick={() => handleMenuClick("시리즈 작성")}
        >
          <div className="icon"></div>
          <div className="text">새글쓰기</div>
        </div>
        <div
          className={`book-mark menu ${
            selected === "북마크" ? "selected" : ""
          }`}
          onClick={() => handleMenuClick("북마크")}
        >
          {selected === "북마크" ? (
            <BsBookmarkFill className="icon" />
          ) : (
            <BsBookmark className="icon" />
          )}{" "}
          <div className="text">북마크</div>
        </div>
        <div
          className={`my-page menu ${
            selected === "마이일지" ? "selected" : ""
          }`}
          onClick={() => handleMenuClick("마이일지")}
        >
          {selected === "마이일지" ? (
            <BsPersonFill className="icon" />
          ) : (
            <BsPerson className="icon" />
          )}{" "}
          <div className="text">마이일지</div>
        </div>
      </div>
    </StyledNavbar>
  );
}

const StyledNavbar = styled.nav`
  z-index: 1;
  height: 5.125rem;
  width: 390px;

  position: fixed;
  bottom: 0;

  background-color: white;
  border-radius: 25px 25px 0 0;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  .container {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    padding: 0 1.5rem 0 1.5rem;
    padding: auto;
    font-size: 0.75rem;
    .menu {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      padding: 0.5rem;
      margin-top: 0.7rem;
      width: 60px;
      text-align: center;
      .icon {
        font-size: 1.2rem;
        margin-bottom: 0.7rem;
        height: 60%;
      }
      .selected {
        fill: #3f910c;
      }
      .text {
      }
    }
    .plus-button {
      z-index: 1000;
      position: fixed;
      bottom: 3.125rem;
      right: 10.65625rem;

      width: 3.0625rem;
      height: 3.0625rem;

      display: flex;
      justify-content: center;
      align-items: baseline;

      border-radius: 50%;
      background-color: #3f910c;
      color: white;
      box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;

      font-size: 3.0625rem;
      font-weight: 300;
    }
  }
`;
