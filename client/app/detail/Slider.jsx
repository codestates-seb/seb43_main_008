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

export const Slider = () => {
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
  );
};

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
