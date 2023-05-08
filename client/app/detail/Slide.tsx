"use client";

import styled from "styled-components";

interface SlideProps {
  id: number;
  nickName: string;
  image: string;
  usageCount: number;
}
const Slide: React.FC<SlideProps> = ({
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
          이 새로운 함수는 내부 함수 func를 호출하는 역할을 하며, 함수를 호출할
          때 인자를 받습니다. (...args)는 인자를 받는 매개변수입니다. <br />
          따라서, throttle 함수가 반환하는 함수를 호출할 때, 인자를 전달하면
          해당 인자가 내부 함수 func로 전달되며, 이 인자를 이용하여 원래 함수의
          실행 결과를 반환합니다. 예를 들어, 다음과 같이 throttle 함수를
          사용한다고 가정해봅시다.
        </p>
      </div>
    </StyledSlide>
  );
};

const StyledSlide = styled.div`
  width: 80vw;
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
