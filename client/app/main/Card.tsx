"use client";

import styled from "styled-components";
import { FaSortDown } from "react-icons/fa";

// 📌 매번 타입 지정하는게 맞나?
interface CardProps {
  nickName: string;
  image: string;
  usageCount: number;
}

export default function Card({ nickName, image, usageCount }: CardProps) {
  return (
    <StyledCard>
      <div className="info">
        <div className="nickName">{nickName} | </div>
        <div className="usageCount">{usageCount}번 사용</div>
      </div>
      {/* img 태그는  background 속성 적용 안됨 -> div 태그로 변경*/}
      <div className="image" style={{ backgroundImage: `url(${image})` }}></div>
      <div className="status">
        <div className="comment">
          <FaSortDown />
          댓글+2
        </div>
        <div className="vote">투표하기 &gt; </div>
      </div>
    </StyledCard>
  );
}

const StyledCard = styled.div`
  width: 100%;
  padding: 1.3rem;
  margin: 0.6rem;

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

  /* 📌 이미지는 항상 중앙을 중심으로 크롭??? 
  만약 사용자가 지정한다면 저장은 어떻게하지? 
  */
  .image {
    height: 35vw;
    width: 100%;
    margin-bottom: 0.6rem;

    background-position: center;
    background-size: cover;
    border-radius: 5px;
  }

  .status {
    font-size: 0.75rem;
    color: #757575;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    .comment {
      margin-right: 0.8rem;
    }
  }
  @media screen and (min-width: 768px) {
    .info,
    .status {
      font-size: 1.15rem;
    }
  }
`;
