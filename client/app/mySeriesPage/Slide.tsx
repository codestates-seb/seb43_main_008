"use client";

import styled from "styled-components";

interface SlideProps {
  id: number;
  image: string;
  // date: Date;
}
// const Slide: React.FC<SlideProps> = ({ image, date }: SlideProps) => {
const Slide: React.FC<SlideProps> = ({ image }: SlideProps) => {
  return (
    <StyledSlide>
      <div className="slide">
        <div className="image" style={{ backgroundImage: `url(${image})` }} />
        <p>
          가만 보면 너는 진짜 너무해 때론 날 무심하게 내팽게 친 채 불안함이란
          벌을 내려줘 그럴 때면 난 길을 잃은 강아지가 돼 고개를 푹 숙인 채 걷네
          그냥 난 네 품에 안겨있고 싶은데 날이 선 너의 말들에 찔려서 피가 났어
          난 근데 걱정은커녕 혼자 낫는 법을 너는 알려줘 그러다가도 헷갈리게
          가끔은 예쁨을 내게 줘 그럴 때면 나는 어떻게 해야 될지 몰라 난 와르르
          무너지고 말아 나의 맘은 와르르 내게 이러지마 baby 너 없는 하루는
          상상하기 조차 싫은 걸 아무것도 할 수 없을 걸 난 바보가 돼버릴거야~
        </p>
        {/* <div className="date">{date.toLocaleDateString()}|</div> -> 하단에 날짜도 찍히게 변경 */}
      </div>
    </StyledSlide>
  );
};

const StyledSlide = styled.div`
  width: 80vw;
  padding: 10px 10px;
  margin: 5px 5px 0 0px;

  display: flex;
  flex-direction: column;
  justify-content: center;

  background-color: white;
  border-radius: 3px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;

  .info {
    display: flex;
    flex-direction: row;
    font-size: 13px;
    color: #757575;
    margin-bottom: 0.6rem;
    .nickName {
      margin-right: 0.1rem;
    }
  }
  .image {
    height: 50vw;
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
