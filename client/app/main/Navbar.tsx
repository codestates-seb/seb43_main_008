"use client";

import Image from 'next/image';
import { useState } from "react";
import { BsPlusLg } from "react-icons/bs";
import styled from "styled-components";

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
            <Image
              className='icon'
              src="/icons/HomeFill.svg"
              width={26}
              height={26}
              alt="홈"
            />
          ) : (
            <Image
              className='icon'
              src="/icons/Home.svg"
              width={26}
              height={26}
              alt="홈"
            />
          )}
          <div className="text">홈</div>
        </div>
        <div
          className={`follow menu ${selected === "팔로우" ? "selected" : ""}`}
          onClick={() => handleMenuClick("팔로우")}
        >
          {selected === "팔로우" ? (
            <Image
              className='icon'
              src="/icons/HeartFill.svg"
              width={26}
              height={26}
              alt="팔로우"
            />
          ) : (
            <Image
              className='icon'
              src="/icons/Heart.svg"
              width={26}
              height={26}
              alt="팔로우"
            />
          )}
          <div className="text">팔로우</div>
        </div>
        <div className="plus-button">
          <BsPlusLg />
        </div>
        <div
          className={`add-series menu ${selected === "시리즈 작성" ? "selected" : ""
            }`}
          onClick={() => handleMenuClick("시리즈 작성")}
        >
          <div className="icon" />
          <div className="text">새글쓰기</div>
        </div>
        <div
          className={`book-mark menu ${selected === "북마크" ? "selected" : ""
            }`}
          onClick={() => handleMenuClick("북마크")}
        >
          {selected === "북마크" ? (
            <Image
              className='icon'
              src="/icons/BookmarkFill.svg"
              width={26}
              height={26}
              alt="북마크"
            />
          ) : (
            <Image
              className='icon'
              src="/icons/Bookmark.svg"
              width={26}
              height={26}
              alt="북마크"
            />
          )}
          <div className="text">북마크</div>
        </div>
        <div
          className={`my-page menu ${selected === "마이일지" ? "selected" : ""
            }`}
          onClick={() => handleMenuClick("마이일지")}
        >
          {selected === "마이일지" ? (
            <Image
              className='icon'
              src="/icons/MyFill.svg"
              width={26}
              height={26}
              alt="마이페이지"
            />
          ) : (
            <Image
              className='icon'
              src="/icons/My.svg"
              width={26}
              height={26}
              alt="마이페이지"
            />
          )}
          <div className="text">마이일지</div>
        </div>
      </div>
    </StyledNavbar>
  );
}

const StyledNavbar = styled.nav`
  z-index: 1;
  height: 4.5rem;
  width: 100%;
  max-width: 1024px;

  position: sticky;
  bottom: 0;

  background-color: white;
  border-radius: 25px 25px 0 0;
  box-shadow: rgba(0, 0, 0, 0.08) 0px -10px 15px;

  color: #222;
  .container {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    padding: 0 1rem;
    padding: auto;
    font-size: 0.75rem;
    .menu {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      padding: 0.5rem;
      margin-top: 0.4rem;
      width: calc(100% / 5);
      text-align: center;
      .icon {
        font-size: 1.2rem;
        margin-bottom: 0.5rem;
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
      position: fixed;
      left: 50%;
      transform: translate(-50%, -75%);
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
