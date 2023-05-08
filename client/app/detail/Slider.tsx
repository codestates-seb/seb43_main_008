"use client";

import styled from "styled-components";
import React, { useRef, useState } from "react";

import slides from "../main/list";
import { Slide } from "./Slide";

interface SlideProps {
  id: number;
  nickName: string;
  image: string;
  usageCount: number;
  currentSlide: number;
}

export const Slider = (): JSX.Element => {
  // 마우스 스크롤로 슬라이드 이동을 위해 DOM에 접근한다.
  const scrollRef = useRef<HTMLUListElement>(null);
  const [isDrag, setIsDrag] = useState<boolean>(false);
  const [startX, setStartX] = useState<number>();

  const onDragStart = (e: React.MouseEvent<HTMLUListElement>) => {
    e.preventDefault();
    setIsDrag(true);
    setStartX(e.pageX + scrollRef.current?.scrollLeft);
  };

  const onDragEnd = () => {
    setIsDrag(false);
  };

  const onDragMove = (e: React.MouseEvent<HTMLUListElement>) => {
    if (isDrag) {
      scrollRef.current.scrollLeft = startX - e.pageX;
    }
  };

  // 함수 일반화 및 재사용을 위해 unknown 키워드로 타입 선언
  // 타입 매개변수 이름으로 T 사용함
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
              <Slide {...data} />
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
