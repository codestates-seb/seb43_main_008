"use client";

import { useEffect, useRef } from "react";


interface ScrollProps {
  setPageQuery: React.Dispatch<React.SetStateAction<number>>;
  pageQuery: number;
  countNumber: number
  lastDataLength: number;
}

interface Options {
  root: Element | null;
  rootMargin: string;
  threshold: number;
}

const options: Options = {
  root: null, // 타켓 요소가 "어디에" 들어왔을때 콜백함수를 실행할 것인지 결정. null이면 viewport가 root로 지정한다.
  rootMargin: '0px',
  threshold: 1.0, // 타겟 요소가 얼마나 들어왔을때 콜백함수를 실행할 것인지 결정. 1이면 타겟 요소 전체를 의미한다.
}

export const Scroll: React.FC<ScrollProps> = ({ setPageQuery, pageQuery, countNumber, lastDataLength }) => {
  const target = useRef<HTMLDivElement>(null);

  const callback: IntersectionObserverCallback = (entries, observer) => {
    entries.forEach(entry => {
      if (entry.isIntersecting && lastDataLength >= 12) {
        console.log(`서버에 다음 페이지 요청 보내기 ${pageQuery}`);
        setPageQuery((prev) => prev + 1)
        observer.unobserve(entry.target); // 타겟 요소 관측 중지: 중복 호출 방지
      }
    });
  };
  useEffect(() => {
    console.log(`서버에 다음 페이지 요청 보내기 ${pageQuery}`);
  }, [])

  useEffect(() => {
    const observer = new IntersectionObserver(callback, options);
    if (target.current) {
      observer.observe(target.current); // 타겟 요소 관측 시작
    }
    return () => {
      observer.disconnect(); // 관찰 중지
    };
  }, [lastDataLength]);

  return (
    <div ref={target} />
  )
}
