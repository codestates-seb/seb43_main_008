
"use client"
import styled from 'styled-components'

import { Feed } from './Feed'
import { Profile } from './Profile'
export default function MyPage() {
  /*
  <Members>
    id
    nickName
    introduce
    image
    seriesIdList: []
  
  <Series/>
    id
    (memberId)
    isPublic : 시리즈 공개 여부
    isActive : 재학생 여부
    
    1️⃣ 아직 쓰고 있어서 투표를 안한 상태
    -> 쓰고있는 일지도 공개/비공개 선택 가능
    isPublic: true or false
    isActive: true

    2️⃣ 투표중인 상태 
    isPublic: true
    isActive: true

    3️⃣ 투표가 완료된 상태
    -> 완료된 일지도 공개/비공개 선택 가능
    isPublic: true or false
    isActive: false

    🤚 졸업 상태 확인은 vote result? 
    voteResult: true -> 명예 졸업 -> 세피아 & 메달 
    voteResult: false -> 그냥 졸업 -> 세피아

    <더 필요한 정보 필요>
    - 이미지 (마지막 일지 사진 - 대표 사진으로 사용)

    ----------------------------------------------------
    게시글 조회 시 서버에서 마감 여부 계산해서 
   */

  return (
    <StylePage>
      <Profile />
      <Feed />
    </StylePage>
  )
}

const StylePage = styled.div`
  min-height: calc(100vh - 44px - 70.8px); 
  /* height: auto; */

  max-width: 1024px;
  min-width: 260px;

  margin-bottom: 20vw;
`