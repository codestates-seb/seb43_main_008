"use client";

import React, { useRef, useState } from "react";
import styled from "styled-components";

const CountCard: React.FC = () => {
  const scrollRef = useRef<HTMLUListElement>(null);
  const [isDrag, setIsDrag] = useState<boolean>(false);
  const [startX, setStartX] = useState<number>();

  const onDragStart = (e: React.MouseEvent<HTMLUListElement>) => {
    e.preventDefault();
    setIsDrag(true);
    setStartX(e.pageX + (scrollRef.current?.scrollLeft || 0));
  };

  const onDragEnd = () => {
    setIsDrag(false);
  };

  const onDragMove = (e: React.MouseEvent<HTMLUListElement>) => {
    if (!isDrag || !scrollRef.current || !startX) return;
    scrollRef.current.scrollLeft = startX - e.pageX;
  };

  // 슬라이드 데이터 배열
  const slidesData = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1];

  return (
    <CountCardContainer>
      <CounterText>사용 횟수</CounterText>
      <PlasticListContainer>
        <ul
          className="list-wrapper"
          ref={scrollRef}
          onMouseDown={onDragStart}
          onMouseMove={onDragMove}
          onMouseUp={onDragEnd}
          onMouseLeave={onDragEnd}
        >
          {slidesData.map((number) => (
            <li key={number} className="my-plastic">
              <div className="plastic-circle">{number}</div>
            </li>
          ))}
        </ul>
      </PlasticListContainer>
    </CountCardContainer>
  );
};

const CountCardContainer = styled.div`
  flex: 1;
  color: #222;
  background-color: white;
  display: flex;
  align-items: center;
  flex-direction: column;
  padding: 5px 10px 10px 10px;
  border-radius: 10px;
  align-items: flex-start;
  margin-top: 7px;
`;

const CounterText = styled.div`
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 3px;
  margin-left: 7px;
`;

const PlasticListContainer = styled.div`
  border: 1px solid #9b9ba0;
  padding: 5px;
  border-radius: 5px;
  background-color: white;
  position: sticky;
  top: 74px;
  width: 100%;
  overflow-x: hidden;

  .list-wrapper {
    display: flex;
    flex-direction: row;
    overflow: scroll;
    transition: 0.3s ease-in;
  }

  .list-wrapper::-webkit-scrollbar {
    display: none;
  }

  .my-plastic {
    display: flex;
    align-items: center;
    margin-bottom: 3px;
    margin-right: 11px;
  }

  .plastic-circle {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    background-color: #fff8de;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
  }
`;

export default CountCard;
