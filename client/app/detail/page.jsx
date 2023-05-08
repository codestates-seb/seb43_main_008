"use client";

import styled from "styled-components";
import { useRef, useState } from "react";
import slides from "../main/list";
import { Slide } from "./Slide";
import { StyledCard } from "./Card";
import { AiOutlineLeft } from "react-icons/ai";
import { FaRegThumbsUp, FaRegThumbsDown } from "react-icons/fa";

// interface SlideProps {
//   index: number;
//   id: number;
//   nickName: string;
//   image: string;
//   usageCount: number;
//   currentSlide: number;
// }

export default function Detail() {
  // 마우스 스크롤로 슬라이드 이동을 위해 DOM에 접근한다.
  const scrollRef = useRef(null);
  const [isDrag, setIsDrag] = useState(false);
  const [startX, setStartX] = useState();

  const onDragStart = (e) => {
    e.preventDefault();
    setIsDrag(true);
    setStartX(e.pageX + scrollRef.current.scrollLeft);
  };

  const onDragEnd = () => {
    setIsDrag(false);
  };

  const onDragMove = (e) => {
    if (isDrag) {
      scrollRef.current.scrollLeft = startX - e.pageX;
    }
  };

  const throttle = (func, ms) => {
    let throttled = false;
    return (...args) => {
      if (!throttled) {
        throttled = true;
        setTimeout(() => {
          func(...args);
          throttled = false;
        }, ms);
      }
    };
  };

  const delay = 10;
  const onThrottleDragMove = throttle(onDragMove, delay);

  return (
    <StyledDetail>
      <Header>
        <AiOutlineLeft size="21" className="icon" />
        <div className="title">봉다리 세바스찬</div>
        <div className=""></div>
      </Header>

      <StyledSlider>
        <section className="container">
          <ul
            className="slider-container"
            ref={scrollRef}
            onMouseDown={onDragStart}
            onMouseMove={onThrottleDragMove}
            onMouseUp={onDragEnd}
            onMouseLeave={onDragEnd}
          >
            {slides.map((data) => (
              <li key={data.id}>
                <Slide key={data.id} {...data} />
              </li>
            ))}
          </ul>
        </section>
      </StyledSlider>

      <h3 className="sub-title">그만 써도 될까요?</h3>
      <StyledCard className="vote-box">
        <div className="vote">
          <div className="text">네!</div>
          <FaRegThumbsUp className="icon" />
        </div>
        <div className="vote">
          <FaRegThumbsDown className="icon" />
          <div className="text">안돼요</div>
        </div>
      </StyledCard>

      <h3 className="sub-title">댓글 달기</h3>
      <StyledCard className="comment">
        <h3 className="sub-title">댓글 달기</h3>
      </StyledCard>
    </StyledDetail>
  );
}

const Header = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;

  height: 67.227px;
  margin: 0 12px;

  font-size: 18px;
  .title {
    margin-right: 21px;
  }
`;

const StyledSlider = styled.div`
  width: 100%;
  min-width: 390px;

  display: flex;
  flex-direction: column;
  position: relative;
  color: #222;

  .container {
    position: relative;
    width: 100%;
    overflow-x: hidden;
  }

  .slider-container {
    display: flex;
    flex-direction: row;
    overflow-x: scroll;
    width: 100%;
    padding-bottom: 24px;
    transition: 0.3s ease-in;
  }

  .slider-container::-webkit-scrollbar {
    display: none;
  }
`;

const StyledDetail = styled.div`
  .sub-title {
    margin: 0 24px;
  }
  .vote-box {
    display: flex;
    flex-direction: row;
    .vote {
      cursor: pointer;
      display: flex;
      flex-direction: row;
      margin: 0 12px;
      .text {
        margin: 0 8px;
      }
      .icon {
        border: 50%;
        /* padding: 12px; */
        /* border: solid; */
      }
    }
  }
`;
