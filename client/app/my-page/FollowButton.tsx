"use client";

import { useRouter } from 'next/navigation'
import styled from 'styled-components';


export const FollowerButton = () => {

  const router = useRouter();

  return (
    <StyledFollowerButton onClick={() => router.push("/follow")}>
      팔로워
    </StyledFollowerButton>
  )
}

interface Props {
  isFollowed?: boolean
}

export const FollowingButton: React.FC<Props> = ({ isFollowed }) => {
  return (
    <>
      {isFollowed ? (
        <StyledFollowingButton>팔로우</StyledFollowingButton>
      ) : (
        <StyledUnFollowingButton>언팔로우</StyledUnFollowingButton>
      )}
    </>
  );
};

const StyledFollowerButton = styled.button`
    cursor: pointer;
    height: 2rem;
    border-radius: 16px;
    border: solid 1px #3f910c;
    background-color: #eff4e7;
    color: #3f910c;
`
const StyledFollowingButton = styled.button`
    cursor: pointer;
    height: 2rem;
    border-radius: 16px;
    border: solid 1px #3f910c;
    background-color: #eff4e7;
    color: #3f910c;
`
const StyledUnFollowingButton = styled.button`
    cursor: pointer;
    height: 2rem;
    border-radius: 16px;
    border: solid 1px #85858e;
    background-color: #f5f2f0;
    color: #85858e;
`

