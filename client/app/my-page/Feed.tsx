"use client";

import styled from 'styled-components';

import { ActivePost } from "./Activepost"
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
  padding: 24px;

  display: flex;
  flex-direction: row;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: 24px 4.5%;

  .feed-item {
    width: 30%;
    height: calc(80vw / 3);

    /* &.active {
        border: solid 1px #3f910c;
        box-shadow: #eff8e0 6px 8px 10px;
      } */
  }
`

// const StyleSeries = styled.div`
//   background-color: white;
//   border-radius: 8px;
//   box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
// `

