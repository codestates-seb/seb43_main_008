"use client";


import styled from 'styled-components';
export const Feed = () => {
  return (
    <StyledFeed className='feed'>
      <StyleSeries className='feed-item active' />
      <StyleSeries className='feed-item' />
      <StyleSeries className='feed-item' />
      <StyleSeries className='feed-item' />
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

    &.active {
        border: solid 1px #3f910c;
        box-shadow: #eff8e0 6px 8px 10px;
      }
  }

  /* 내용물을 부모 요소에 맞춰서 위치시킴 */
  /* .feed-item > * {
    position: absolute; 
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
  } */
`

const StyleSeries = styled.div`
  background-color: white;
  border-radius: 8px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
`