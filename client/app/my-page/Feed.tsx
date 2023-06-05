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
  const [isVoteEnded, setIsVoteEnded] = useState(false);

  const params = useParams();
  let nickName = decodeURIComponent(params.nickName);

  useEffect(() => {
    if (type === "mine") {
      GetMyFeed().then((data) => {
        if (data) {
          setPost(data);
          // 투표가 종료되었는지를 확인하기 위한 코드
          const currentTime: Date = new Date();
          const voteEndAt: Date = new Date(data.voteEndAt);
          setIsVoteEnded(currentTime > voteEndAt);
        }
      });
    }
    else GetFeed(nickName).then((data) => {
      if (data) {
        setPost(data);
        // 투표가 종료되었는지를 확인하기 위한 코드
        const currentTime: Date = new Date();
        const voteEndAt: Date = new Date(data.voteEndAt);
        setIsVoteEnded(currentTime > voteEndAt);
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
              return <ActivePost key={data.id} {...data} type={type} />;
            }
            if (data.seriesStatus === "SERIES_SLEEP") {
              // sleep: 투표 중
              if (data.voteResult) {
                return <DonePost key={data.id} {...data} />; // 1차 투표에서 통과하면 바로 done ui 생성
              } else {
                return <VotingPost key={data.id} {...data} type={type} isVoteEnded={isVoteEnded} />;
              }
            }
            if (data.seriesStatus === "SERIES_QUIT") {
              // quiet: 투표 종료
              return <DonePost key={data.id} {...data} />;
            }
            return null; // 추가: 다른 경우에는 null을 반환하여 렌더링되지 않도록 함
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
