
"use client"
import styled from 'styled-components'

import { Feed } from './Feed'
import { Profile } from './Profile'
export default function MyPage() {
  return (
    <StylePage>
      <Profile />
      <Feed />
    </StylePage>
  )
}

const StylePage = styled.div`
  height: calc(100vh - 110px);
`