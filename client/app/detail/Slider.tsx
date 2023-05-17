"use client";

import React, { useRef, useState } from "react";
import styled from "styled-components";

import slides from "./list";
import { Slide } from "./Slide";
export const Slider = (): JSX.Element => {
  // 🚨 렌더되기 전에 슬라이더 조작하면 에러남. 
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
      const { scrollWidth, clientWidth, scrollLeft } = scrollRef.current;

      scrollRef.current.scrollLeft = startX - e.pageX;
      console.log("함수 실행중")

      if (scrollWidth <= Math.ceil(clientWidth + scrollLeft)) {
        console.log("서버에 다음 페이지 요청하기 & 요청중이라면 재요청 안보내기")
      }

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
    overflow: scroll;
    width: 100%;
    padding-bottom: 24px;
    margin-top: 10px;
    transition: 0.3s ease-in;
  }

  .slider-container::-webkit-scrollbar {
    display: none;
  }
`;
