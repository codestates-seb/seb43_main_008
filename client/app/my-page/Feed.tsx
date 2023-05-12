"use client";

import styled from 'styled-components';

import { ActivePost } from './ActivePost';
import { DonePost } from "./DonePost"
export const Feed = () => {
  // map 돌려서 게시글 불러오기 

  return (
    <StyledFeed className='feed'>
      <ActivePost />
      <DonePost />
      <DonePost />
      <DonePost />
    </StyledFeed>
  )
}

const StyledFeed = styled.div`
  position: relative;
  padding: 24px;

  display: flex;
  flex-direction: row;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: 24px 0%;
`


