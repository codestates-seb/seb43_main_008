"use client";

// import { useEffect } from 'react';
import styled from 'styled-components';

// import { GetMembers } from "../api/api"
import { ActivePost } from './ActivePost';
import { DonePost } from "./DonePost"
import { postData } from './postData';
import { VotingPost } from "./VotingPost"

export const Feed = () => {
  // map 돌려서 게시글 불러오기 

  /* 로그인 되면 수정
  useEffect(() => {
    if (!authState) {
      router.push("/login")
    }
    GetMembers().then((data) => {
      if (data) {
        setList(data)
      }
    })
  }, [])
   */

  console.log(postData)
  return (

    <StyledFeed className='container'>
      <div className='feed'>
        {postData.map((data) => {
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


