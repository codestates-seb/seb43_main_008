"use client";

import { useState } from "react";
import styled from "styled-components";

import slides from "../main/list";
import { Slide, StyledSeries } from "./Card";

// interface SlideProps {
//   index: number;
//   id: number;
//   nickName: string;
//   image: string;
//   usageCount: number;
//   currentSlide: number;
// }

export default function Detail() {
  const [currentSlide, setCurrentSlide] = useState(0);

  const PrevButton: React.FC = () => {
    return (
      <button
        onClick={() =>
          setCurrentSlide(currentSlide === 0 ? 0 : currentSlide - 1)
        }
      >
        prev
      </button>
    );
  };

  const NextButton: React.FC = () => {
    return (
      <button
        onClick={() =>
          setCurrentSlide(
            currentSlide === slides.length - 1
              ? slides.length - 1
              : currentSlide + 1
          )
        }
      >
        next
      </button>
    );
  };

  return (
    <>
      <TopNavBar />
      <StyledDetail>
        <section className="slide-show">
          {slides.map((data, index) => (
            <StyledSeries key={data.id}>
              <Slide
                key={data.id}
                {...data}
                index={index}
                currentSlide={currentSlide}
              />
            </StyledSeries>
          ))}
          <PrevButton />
          <NextButton />
        </section>
        <section className="vote">
          <h3 className="sub-title">그만 써도 될까요?</h3>
        </section>
        <section className="comment">
          <h3 className="sub-title">댓글</h3>
        </section>
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

  display: flex;
  flex-direction: column;
  position: relative;
  padding: 1.6rem 0.5rem 1.6rem 0.5rem;
  color: #222;

  .slide-show {
    position: relative;
    height: 500px;
    overflow: hidden;
  }

  .slide {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    opacity: 0;
    transition: opacity 1s ease;
    background-color: green;
  }
`;
