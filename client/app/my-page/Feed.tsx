"use client";

import styled from 'styled-components';

import { ActivePost } from './ActivePost';
import { DonePost } from "./DonePost"
import { VotingPost } from './VotingPost';

export const Feed = () => {
  // map 돌려서 게시글 불러오기 

  return (

    <StyledFeed >
      <div className='feed'>
        <ActivePost />
        <VotingPost voting={true} />
        <VotingPost voting={false} />
        <DonePost level={1} />
        <DonePost level={1} />
        <DonePost level={1} />
        <DonePost />
      </div>

    </StyledFeed>
  )
}

const StyledFeed = styled.div`
  width: 100%;

  padding: 24px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;


  .feed {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    /* justify-content: flex-start; */
    flex-wrap: wrap;
    gap: 12px 4%;
  }
`


