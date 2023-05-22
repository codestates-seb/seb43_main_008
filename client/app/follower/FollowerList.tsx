"use client"
import { useEffect, useState } from 'react';
import styled from 'styled-components'

import { GetFollowing } from "../api/followApi"
import { Follow } from './Follower';
export const FollowList = () => {
  const [followingList, setFollowingList] = useState([])


  useEffect(() => {
    GetFollowing().then((data) => {
      if (data) {
        setFollowingList((data))
      }
    })
  }, [])

  return (
    <StylePage>
      {followingList.length > 0 ? followingList.map((data) =>
        <Follow
          key={data.nickName}
          {...data} />
      ) :
        <div className='empty-box'> 나를 팔로우하는 사람이 없습니다.</div>
      }

    </StylePage>
  )
}

const StylePage = styled.div`
  min-height: calc(100vh - 110px - 44px); 
  margin-bottom: 100px;

  display: flex;
  justify-content: center;
`