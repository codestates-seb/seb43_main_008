"use client";
import styled from "styled-components";

export const EmptyFeed = (content: string) => {
  return (
    <StyledEmptyFeed>
      <div> 작성된 글이 없습니다. </div>
    </StyledEmptyFeed>
  )
}

const StyledEmptyFeed = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  height: min-content;
`