"use client";

import { useEffect, useState } from 'react';
import styled from 'styled-components';

import { GetFeed } from "../api/myPageApi"
import { ActivePost } from './ActivePost';
import { DonePost } from "./DonePost";
import { EmptyFeed } from "./EmptyFeed"
import { VotingPost } from "./VotingPost";

export const Feed = () => {
  const [post, setPost] = useState([])

  useEffect(() => {
    // if (!authState) {
    //   router.push("/login")
    // }
    GetFeed().then((data) => {
      if (data) {
        setPost(data)
      }
    })
  }, [])

  return (
    <StyledFeed className='container'>
      {post.length > 0 ?
        (
          <div className='feed'>
            {post.map((data) => {
              if (data.seriesStatus === "SERIES_ACTIVE") { // active: 투표전
                return <ActivePost key={data.id}  {...data} />
              }
              if (data.seriesStatus === "SERIES_SLEEP") { // sleep: 투표 중
                return <VotingPost key={data.id} {...data} />
              }
              if (data.seriesStatus === "SERIES_QUIET") { // quiet: 투표 종료
                return <DonePost key={data.id} {...data} />
              }
            })}
          </div>
        ) : <EmptyFeed />}


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
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    grid-template-rows: repeat(3, 1fr);

    grid-gap: 4vw
  }

  @media screen and (min-width: 1024px) {
    .image {
      width: 250px;
      height: 250px;
      font-size: 46px;
    }
  }
`


