"use client";

import { useRouter } from 'next/navigation'
import { useEffect, useState } from 'react';
import styled from 'styled-components';

import { DeleteUnFollowing, PostFollowing } from "../api/followApi"

export const FollowerButton = () => {

  const router = useRouter();

  return (
    <StyledFollowerButton onClick={() => router.push("/follower")}>
      팔로워
    </StyledFollowerButton>
  )
}

interface Props {
  followedMember?: boolean
  nickName?: string
}

export const FollowingButton: React.FC<Props> = ({ followedMember, nickName }) => {
  const [isFollowing, setIsFollowing] = useState<boolean>(followedMember || false)

  useEffect(() => {
    setIsFollowing(followedMember || false);
  }, [followedMember]);

  console.log(isFollowing)

  return (
    <>
      {isFollowing === true ? (
        <StyledUnFollowingButton
          onClick={() => {
            DeleteUnFollowing(nickName)
            setIsFollowing(false)
          }}
        >
          언팔로우
        </StyledUnFollowingButton>
      ) : (
        <StyledFollowingButton
          onClick={() => {
            PostFollowing(nickName)
            setIsFollowing(true)
          }}
        >
          팔로우
        </StyledFollowingButton>
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

