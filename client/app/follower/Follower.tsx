"use client";

import Image from 'next/image';
import styled from "styled-components";

interface FollowProps {
  image: string;
  nickName: string;
  introduce: string;
}
export const Follow: React.FC<FollowProps> = ({ image, nickName, introduce }) => {
  return (
    <StyledFollow>
      <div className='profile-box'>
        <Image
          className='image'
          src={image}
          width={40}
          height={40}
          alt="프로필 사진"
        />
        <div className='info-box'>
          <div className='nick-name'>{nickName}</div>
          <div className='user-info'>{introduce}</div>
        </div>
        <Image
          className='icon'
          src="/icons/HeartFill.svg"
          width={26}
          height={26}
          alt="팔로우 아이콘"
        />
      </div>
    </StyledFollow>
  )
}

const StyledFollow = styled.div`
  height: 70px;
  padding: 24px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100vw;


  .profile-box{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: flex-start;
  }

  .info-box{
    flex-grow: 8;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    margin-right: 18px;
    .user-info {
    margin-top: 4.3px;
    font-size: 0.8rem;
    color: #757575;
    }
  }

  .image {
    border-radius: 50%;
    margin-right: 18px;
  }
`