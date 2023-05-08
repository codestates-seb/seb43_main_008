"use client";

import styled from "styled-components";
import { useRef, useState } from "react";
import slides from "../main/list";
import { Slide } from "./Slide";

// interface SlideProps {
//   index: number;
//   id: number;
//   nickName: string;
//   image: string;
//   usageCount: number;
//   currentSlide: number;
// }

export default function Detail() {
  // 마우스 스크롤로 슬라이드 이동을 위해 DOM에 접근한다.1
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

  const delay = 100;
  const onThrottleDragMove = throttle(onDragMove, delay);

  return (
    <>
      <TopNavBar />
      <StyledDetail>
        <section className="container">
          <ul
            className="slider-container"
            ref={scrollRef}
            onMouseDown={onDragStart}
            onMouseMove={onThrottleDragMove}
            onMouseUp={onDragEnd}
            onMouseLeave={onDragEnd}
          >
            {slides.map((data, index) => (
              <li key={data.id}>
                <Slide key={data.id} {...data} index={index} />
              </li>
            ))}
          </ul>
        </section>

        {/* <section className="vote">
          <h3 className="sub-title">그만 써도 될까요?</h3>
        </section>
        <section className="comment">
          <h3 className="sub-title">댓글</h3>
        </section> */}
      </StyledDetail>
    </>
  );
}

const TopNavBar = styled.div`
  top: 0;
  width: 100%;
  height: 65px;
  border: solid;
`;

const StyledDetail = styled.div`
  width: 100%;
  min-width: 390px;
  height: 100vh;
  /* padding: 24px; */

  display: flex;
  flex-direction: column;
  position: relative;
  color: #222;

  .container {
    position: relative;
    width: 100%;
    overflow-x: hidden;
    height: 1000px;
  }

  .slider-container {
    display: flex;
    flex-direction: row;
    overflow-x: scroll;
    /* position: absolute; */
    width: 100%;
  }

  .slider-container.animated {
    -webkit-transition: left 0.3s ease-in;
    transition: left 0.3s ease-in;
  }
`;
