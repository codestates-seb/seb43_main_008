"use client";

import { useRouter } from "next/navigation";
import styled from "styled-components";

import { BookmarkButton } from "./BookmarkButton"

interface SlideProps {
  content: string;
  contentImg: string;
  daylogNumber: string;
  series: {
    title: string,
    id: string
  },
  member: {
    nickName: string,
  }
  count: number
}
const Slide = ({
  contentImg,
  content,
  series,
  daylogNumber,
  member,
}: SlideProps) => {
  const router = useRouter();

  const moveToMypageHandler = (nickName: string) => {
    router.push(`my-page/${nickName}`)
    sessionStorage.setItem("header", nickName);
  }

  return (
    <StyledSlide>
      <div className="slide">
        <div className="info">
          <div className='left-box' onClick={() => moveToMypageHandler(member.nickName)}>
            <div className='user-info'>{member.nickName}</div>
            <div className='series-info'>
              <div className="nickName">{series.title} | </div>
              <div className="usageCount">{daylogNumber}번째 사용</div>
            </div>

          </div>
          <div className='right-box'>
            <BookmarkButton />
          </div>
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
    justify-content: space-between;
    align-items: flex-start;
    font-size: 0.75rem;
    color: #757575;
    margin-bottom: 0.6rem;
    .left-box {
      display: flex;
      flex-direction: column;
    }
    .user-info {
      cursor: pointer;
      padding: 2px 4px;
      margin-bottom: 4px;
      width: fit-content;
      color: #3f910c;
      background-color: #eff4e7;
      border: solid 1px #3f910c;
      border-radius: 8px;
    }
    .series-info {
      display: flex;
    }
    .nickName {
      margin-right: 0.1rem;
    }
    .right-box{
      font-size: 1.1rem;
      cursor: pointer;
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
