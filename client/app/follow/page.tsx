"use client"

import styled from 'styled-components'

import { Follows } from './Follow';

export default function Follow() {
  return (
    <StylePage>
      <Follows />
      <Follows />
      <Follows />
      <Follows />
      <Follows />
      <Follows />
      <Follows />
      <Follows />
      <Follows />
      <Follows />
      <Follows />
      <Follows />
      <Follows />
      <Follows />
    </StylePage>

  )
}

const StylePage = styled.div`
  height: calc(100vh - 110px);
  display: flex;
  flex-direction: column;
  height: 100vh;

`