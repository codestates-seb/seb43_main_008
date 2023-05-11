"use client";

import { useState } from "react";
import { AiFillHome, AiOutlineHome } from "react-icons/ai";
import { BsBookmarkFill, BsHeartFill, BsPersonFill } from "react-icons/bs";
import { BsBookmark, BsHeart, BsPerson } from "react-icons/bs";
import { BsPlusLg } from "react-icons/bs";
import styled from "styled-components";

// import Bookmark from "../public/icons/Bookmark.svg"; // ?

export default function Navbar() {
  // 클릭 메뉴 관리 함수
  const [selected, setSelected] = useState("홈");

  const handleMenuClick = (menuName: string) => {
    setSelected(menuName);
  };

  // 스크롤 이벤트 감지 함수
  /* 
  const [isNavOn, setIsNavOn] = useState<boolean>(true);

  const throttle = <T extends unknown[]>(
    func: (...args: T) => void,
    ms: number
  ) => {
    let throttled = false;
    return (...args: T) => {
      if (!throttled) {
        throttled = true;
        setTimeout(() => {
          func(...args);
          throttled = false;
        }, ms);
      }
    };
  };

  const scrollDirection = () => {
    const beforeScrollY = useRef<number>(window.pageYOffset);

    if (window.pageYOffset > beforeScrollY.current) {
      setIsNavOn(false);
      console.log("켜기");
    } else {
      setIsNavOn(true);
      console.log("끄기");
    }

    beforeScrollY.current = window.pageYOffset;
  };

  const scrollEvent: any = useMemo(throttle(scrollDirection, 100), [isNavOn]);

  useEffect(() => {
    window.addEventListener("scroll", scrollEvent);
    return () => {
      window.removeEventListener("scroll", scrollEvent);
    };
  }, [scrollEvent]);
  */

  return (
    // <StyledNavbar style={{ display: isNavOn ? "block" : "none" }}>
    <StyledNavbar>
      <div className="container">
        <div
          className={`home menu ${selected === "홈" ? "selected" : ""}`}
          onClick={() => handleMenuClick("홈")}
        >
          {selected === "홈" ? (
            <AiFillHome className="home icon" />
          ) : (
            <AiOutlineHome className="home icon" />
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
        <div className="plus-button">
          <BsPlusLg />
        </div>
        <div
          className={`add-series menu ${
            selected === "시리즈 작성" ? "selected" : ""
          }`}
          onClick={() => handleMenuClick("시리즈 작성")}
        >
          <div className="icon" />
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
          )}
          <div className="text">북마크</div>
        </div>
        <div
          className={`my-page menu ${
            selected === "마이일지" ? "selected" : ""
          }`}
          onClick={() => handleMenuClick("마이일지")}
        >
          {selected === "마이일지" ? (
            <BsPersonFill className="my-page icon" />
          ) : (
            <BsPerson className="my-page icon" />
          )}
          <div className="text">마이일지</div>
        </div>
      </div>
    </StyledNavbar>
  );
}

const StyledNavbar = styled.nav`
  z-index: 1;
  height: 5.125rem;
  width: 100%;
  max-width: 1024px;

  position: sticky;
  bottom: 0;

  background-color: white;
  border-radius: 25px 25px 0 0;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;

  color: #222;
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
      width: calc(100% / 5);
      text-align: center;
      .icon {
        font-size: 1.2rem;
        margin-bottom: 0.7rem;
        height: 60%;
      }
      .home {
        font-size: 1.4rem;
      }
      .my-page {
        font-size: 1.55rem;
      }
      .text {
      }
    }
    .plus-button {
      font-size: 35px;

      z-index: 1000;
      position: absolute;
      left: 50%;
      transform: translate(-50%, -100%);
      bottom: 0;

      width: 3.0625rem;
      height: 3.0625rem;

      display: flex;
      justify-content: center;
      align-items: center;

      border-radius: 50%;
      background-color: #3f910c;
      color: white;
      box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
    }
  }

  @media screen and (min-width: 768px) {
    .container {
      font-size: 1.15rem;
      .menu {
        .text {
          margin-bottom: 0.5rem;
        }
      }
    }
  }
`;
