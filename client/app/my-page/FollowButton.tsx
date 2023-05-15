"use client";

import styled from 'styled-components';

interface Props {
  type?: string
}
export const FollowButton: React.FC<Props> = ({ type }) => {
  return (
    <>
      {type === "팔로워" ? <StyledFollowerButton>{type}</StyledFollowerButton> :
        type === "팔로우" ? <StyledFollowButton>{type}</StyledFollowButton> :
          <StyledUnFollowButton>{type}</StyledUnFollowButton>
      }
    </>
  )
}

const StyledFollowerButton = styled.button`
    cursor: pointer;
    height: 2rem;
    border-radius: 16px;
    border: solid 1px #3f910c;
    background-color: #eff4e7;
    color: #3f910c;
`
const StyledFollowButton = styled.button`
    cursor: pointer;
    height: 2rem;
    border-radius: 16px;
    border: solid 1px #3f910c;
    background-color: #eff4e7;
    color: #3f910c;
`
const StyledUnFollowButton = styled.button`
    cursor: pointer;
    height: 2rem;
    border-radius: 16px;
    border: solid 1px #85858e;
    background-color: #f5f2f0;
    color: #85858e;
`

