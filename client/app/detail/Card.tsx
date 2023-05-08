"use client";

import styled from "styled-components";

import { StyledCard } from "../main/Card";

interface SlideProps {
  index: number;
  id: number;
  nickName: string;
  image: string;
  usageCount: number;
  currentSlide: number;
}
const Slide: React.FC<SlideProps> = ({
  id,
  index,
  image,
  nickName,
  usageCount,
  currentSlide,
}: SlideProps) => {
  return (
    <div className={index === currentSlide ? "slice active" : "slide"}>
      <div className="nickName">{nickName} | </div>
      <div className="usageCount">{usageCount}번 사용</div>
      <div className="image" style={{ backgroundImage: `url(${image})` }} />
    </div>
  );
};

const StyledSeries = styled(StyledCard)`
  /* width: 80%;  */
  width: 310px;
  padding: 1rem 1.3rem;
  margin: 0.6rem 0.2rem;
`;

export { Slide, StyledSeries };
