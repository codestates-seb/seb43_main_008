"use client";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";
import styled from "styled-components";

import { GetFeed, GetMyFeed } from "../api/myPageApi"
import { ActivePost } from './ActivePost';

import { DonePost } from "./DonePost";
import { EmptyFeed } from "./EmptyFeed";
import { VotingPost } from "./VotingPost";


interface Props {
  type?: string
}

export const Feed: React.FC<Props> = ({ type }) => {
  const [post, setPost] = useState([])
  const params = useParams();
  let nickName = decodeURIComponent(params.nickName);
  console.log(nickName);

  if (nickName === "undefined") {
    nickName = "세션 스토리지에서 가져오기";
  }
  useEffect(() => {
    if (type === "mine") {
      GetMyFeed().then((data) => {
        if (data) {
          setPost(data);
        }
      });
    }
    else GetFeed(nickName).then((data) => {
      if (data) {
        setPost(data);
      }
    });
  }, []);

  return (
    <StyledFeed className="container">
      {post.length > 0 ? (
        <div className="feed">
          {post.map((data) => {
            if (data.seriesStatus === "SERIES_ACTIVE") {
              // active: 투표전
              return <ActivePost key={data.id} {...data} />;
            }
            if (data.seriesStatus === "SERIES_SLEEP") {
              // sleep: 투표 중
              return <VotingPost key={data.id} {...data} />;
            }
            if (data.seriesStatus === "SERIES_QUIET") {
              // quiet: 투표 종료
              return <DonePost key={data.id} {...data} />;
            }
          })}
        </div>
      ) : (
        <EmptyFeed />
      )}
    </StyledFeed>
  );
};

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

    grid-gap: 4vw;
  }

  @media screen and (min-width: 1024px) {
    .image {
      width: 250px;
      height: 250px;
      font-size: 46px;
    }
  }
`;
