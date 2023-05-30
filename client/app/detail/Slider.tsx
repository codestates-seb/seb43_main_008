"use client";

import { useParams } from 'next/navigation';
import React, { useEffect, useRef, useState } from "react";
import styled from "styled-components";

import { GetDaylog } from "../api/detailApi";
import { Slide } from "./Slide";
export const Slider = (): JSX.Element => {

  // api 요청 함수
  const [slides, setSlides] = useState([]);
  const [pageNumber, setPageNumber] = useState(1)
  const [lastDataLength, setLastDataLength] = useState<number>(0)
  const [isLoading, setIsLoading] = useState<boolean>(true)

  const params = useParams();

  useEffect(() => {
    setIsLoading(false)
    GetDaylog(params.id, pageNumber).then((data) => {
      if (data) {
        setSlides((prevList) => [...prevList, ...data])
        setLastDataLength(data.length)
        setIsLoading(true)
      }
    })
  }, [pageNumber])


  // 마우스 스크롤로 슬라이드 이동을 위해 DOM에 접근한다.
  const scrollRef = useRef<HTMLUListElement>(null);
  const [isDrag, setIsDrag] = useState<boolean>(false);
  const [startX, setStartX] = useState<number>();

  // 터치 이벤트인지 확인하는 함수
  const isTouchEvent = (event: React.MouseEvent<HTMLUListElement> | React.TouchEvent<HTMLUListElement>): event is React.TouchEvent<HTMLUListElement> => {
    return "touches" in event;
  };

  const onDragStart = (e: React.MouseEvent<HTMLUListElement> | React.TouchEvent<HTMLUListElement>) => {
    e.preventDefault();

    // 터치 이벤트인 경우에는 터치 좌표를 가져온다. 
    const pageX = isTouchEvent(e) ? e.touches[0].pageX : e.pageX;

    setIsDrag(true);
    setStartX(pageX + (scrollRef.current?.scrollLeft || 0));
  };

  const onDragEnd = () => {
    setIsDrag(false);
  };

  const onDragMove = (e: React.MouseEvent<HTMLUListElement> | React.TouchEvent<HTMLUListElement>) => {
    if (isDrag) {
      const { scrollWidth, clientWidth, scrollLeft } = scrollRef.current;

      // 터치 이벤트인 경우에는 터치 좌표를 가져온다. 
      const pageX = isTouchEvent(e) ? e.touches[0].pageX : e.pageX;

      scrollRef.current.scrollLeft = startX - pageX;
      // 추가 api 요청은 pageNumber에 의존한다.
      // 마지막에 들어온 데이터 길이가 7개 미만이면 pageNumber 변경을 차단시켜 무한 스크롤을 멈춘다. 
      if (scrollWidth === Math.ceil(clientWidth + scrollLeft) && lastDataLength >= 7 && isLoading) {
        setPageNumber((prev) => prev + 1)
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
          // 데스크탑 클릭
          onMouseDown={onDragStart}
          onMouseMove={onThrottleDragMove}
          onMouseUp={onDragEnd}
          onMouseLeave={onDragEnd}
          // 모바일 터치
          onTouchStart={onDragStart}
          onTouchMove={onThrottleDragMove}
          onTouchEnd={onDragEnd}
          onTouchCancel={onDragEnd}
        >
          {slides.map((data) => {
            return <Slide key={`detail ${data.id}`} {...data} len={slides.length} />;
          })}
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
