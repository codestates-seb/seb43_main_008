"use client";

import styled from "styled-components";

interface SlideProps {
  index: number;
  id: number;
  nickName: string;
  image: string;
  usageCount: number;
}
const Slide: React.FC<SlideProps> = ({
  id,
  index,
  image,
  nickName,
  usageCount,
}: SlideProps) => {
  return (
    <StyledSlide>
      <div className="slide">
        <div className="info">
          <div className="nickName">{nickName} | </div>
          <div className="usageCount">{usageCount}번 사용</div>
        </div>

        <div className="image" style={{ backgroundImage: `url(${image})` }} />
        <p>
          여기에 글을 보여줄거양
          <br />
          여기에 글을 보여줄거양
          <br />
          여기에 글을 보여줄거양
          <br />
          여기에 글을 보여줄거양
          <br />
          여기에 글을 보여줄거양
          <br />
          여기에 글을 보여줄거양
          <br />
        </p>
      </div>
    </StyledSlide>
  );
};

const StyledSlide = styled.div`
  width: 80vw;
  padding: 24px 21px;
  margin: 24px 0px 24px 12px;

  display: flex;
  flex-direction: column;
  justify-content: center;

  background-color: white;
  border-radius: 8px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 3px 24px;

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
    height: 35vw;
    width: 100%;
    margin-bottom: 0.73rem;

    background-position: center;
    background-size: cover;
    border-radius: 5px;
  }

  .slide {
    width: 100%;
  }
`;

export { Slide, StyledSlide };
