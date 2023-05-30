"use client";

import Image from 'next/image';
import styled from "styled-components";

export const Follows = () => {
  return (
    <StyledFollow>
      <div className='profile-box'>
        <Image
          className='image'
          src="data:image/jpeg;base64,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"
          width={40}
          height={40}
          alt="프로필 사진"
        />
        <div className='info-box'>
          <div className='nick-name'>닉네임</div>
          <div className='user-info'>한줄 소개</div>
        </div>
        <Image
          className='icon'
          src="/icons/HeartFill.svg"
          width={26}
          height={26}
          alt="팔로우"
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