"use client";

import styled from "styled-components";

import { BookmarkButton } from "./BookmarkButton"

interface SlideProps {
  id: number;
  content: string;
  contentImg: string;
  series: {
    title: string,
    id: string
  },
  count: number
}
const Slide = ({
  id,
  contentImg,
  content,
  series,
}: SlideProps) => {
  return (
    <StyledSlide>
      <div className="slide">
        <div className="info">
          <div className="nickName">{series.title} | </div>
          <div className="usageCount">{id}번째 사용</div>
          <BookmarkButton seriesId={series.id} />
        </div>

        <div className="image" style={{ backgroundImage: `url(${contentImg})` }} />
        <p>
          {content}
        </p>
      </div>
    </StyledSlide>
  );
};

const StyledSlide = styled.div`
  padding: 24px 21px;
  margin: 10px -10px 10px 24px;

  display: flex;
  flex-direction: column;
  justify-content: center;

  background-color: white;
  border-radius: 8px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;

  .info {
    display: flex;
    flex-direction: row;
    font-size: 0.75rem;
    color: #757575;
    margin-bottom: 0.6rem;
    .nickName {
      margin-right: 0.1rem;
    }
  }
  .image {
    height: 25vh;
    width: 100%;
    margin-bottom: 1.5rem;

    background-position: center;
    background-size: cover;
    border-radius: 5px;
  }

  .slide {
    width: 70vw;
    height: 40vh;
    overflow: auto;
  }

  .p {
    overflow: hidden;
    height: 15vh;
  }
`;

export { Slide, StyledSlide };
