"use client";

import Image from "next/image";
import Image from 'next/image';
import Link from "next/link";
import { useParams } from 'next/navigation';
import { BsPlusLg } from "react-icons/bs";
import styled from "styled-components";

export default function Navbar() {

  const params = useParams();
  console.log(params)

  // 클릭 메뉴 관리 함수
  const menuClickHandle = (menuName: string) => {
    sessionStorage.setItem("menu", menuName);
  };

  let selectedMenu = "홈";
  if (typeof window !== "undefined") {
    selectedMenu = sessionStorage.getItem("menu");
  }

  return (
    // <StyledNavbar style={{ display: isNavOn ? "block" : "none" }}>
    <StyledNavbar>
      <div className="container">
        <Link
          href="/"
          className={`home menu ${selectedMenu === "홈" ? "selectedMenu" : ""}`}
          onClick={() => menuClickHandle("홈")}
        >
          {selectedMenu === "홈" ? (
            <Image
              className="icon"
              src="/icons/HomeFill.svg"
              width={26}
              height={26}
              alt="홈"
            />
          ) : (
            <Image
              className="icon"
              src="/icons/Home.svg"
              width={26}
              height={26}
              alt="홈"
            />
          )}
          <div className="text">홈</div>
        </Link>
        <Link
          href="follow"
          className={`follow menu ${
            selectedMenu === "팔로우" ? "selectedMenu" : ""
          }`}
          onClick={() => menuClickHandle("팔로우")}
        >
          {selectedMenu === "팔로우" ? (
            <Image
              className="icon"
              src="/icons/HeartFill.svg"
              width={26}
              height={26}
              alt="팔로우"
            />
          ) : (
            <Image
              className="icon"
              src="/icons/Heart.svg"
              width={26}
              height={26}
              alt="팔로우"
            />
          )}
          <div className="text">팔로우</div>
        </Link>
        <Link href="/baby" className="plus-button">
          <BsPlusLg />
        </Link>
        <div
          className={`add-series menu ${
            selectedMenu === "시리즈 작성" ? "selectedMenu" : ""
          }`}
          onClick={() => menuClickHandle("시리즈 작성")}
        >
          <div className="icon" />
          <div className="text">새글쓰기</div>
        </div>
        <Link
          href="bookmark"
          className={`book-mark menu ${
            selectedMenu === "북마크" ? "selectedMenu" : ""
          }`}
          onClick={() => menuClickHandle("북마크")}
        >
          {selectedMenu === "북마크" ? (
            <Image
              className="icon"
              src="/icons/BookmarkFill.svg"
              width={26}
              height={26}
              alt="북마크"
            />
          ) : (
            <Image
              className="icon"
              src="/icons/Bookmark.svg"
              width={26}
              height={26}
              alt="북마크"
            />
          )}
          <div className="text">북마크</div>
        </Link>
        <Link
          href="my-page"
          className={`my-page menu ${
            selectedMenu === "마이일지" ? "selectedMenu" : ""
          }`}
          onClick={() => menuClickHandle("마이일지")}
        >
          {selectedMenu === "마이일지" ? (
            <Image
              className="icon"
              src="/icons/MyFill.svg"
              width={26}
              height={26}
              alt="마이페이지"
            />
          ) : (
            <Image
              className="icon"
              src="/icons/My.svg"
              width={26}
              height={26}
              alt="마이페이지"
            />
          )}
          <div className="text">마이일지</div>
        </Link>
      </div>
    </StyledNavbar>
  );
}

const StyledNavbar = styled.nav`
  z-index: 10;
  height: 70px;
  max-height: 70px;
  width: 100vw;
  max-width: 1024px;

  position: fixed;
  bottom: 0;

  background-color: white;
  border-radius: 25px 25px 0 0;
  box-shadow: rgba(0, 0, 0, 0.08) 0px -10px 15px;

  color: #222;
  a {
    color: inherit;
    text-decoration: none;
  }
  .container {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    padding: 0 1rem;
    padding: auto;
    font-size: 0.75rem;
    .menu {
      cursor: pointer;
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
        /* font-size: 3.5vw; */
        white-space: nowrap;
      }
    }
    .plus-button {
      cursor: pointer;
      font-size: 35px;

      z-index: 1000;
      position: absolute;
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
