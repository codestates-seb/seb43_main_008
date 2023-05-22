"use client";

import { useEffect, useState } from "react";
import styled from "styled-components";

import FetchToken from "../api/fetchToken";

// import { useSWRConfig } from 'swr'

export const Filter: React.FC = () => {
  useEffect(() => {
    // Hide URL
    history.replaceState({}, null, location.pathname);
  }, []);
  const [filter, setFilter] = useState<string>("newest");
  FetchToken();

  /*
  // filter 값이 변경될 때, 데이터를 다시 요청하기 위해 mutate 함수 호출
  useEffect(() => {
    GetMainFilter(filter)
  }, [filter, mutate]);
  */

  return (
    <StyledFilter>
      <div className="filter">
        <div
          className={filter === "newest" ? "selected tag" : "tag"}
          onClick={() => setFilter("newest")}
        >
          새로운 게시글
        </div>
        <div
          className={filter === "voteCount" ? "selected tag" : "tag"}
          onClick={() => setFilter("voteCount")}
        >
          인기 게시글
        </div>
      </div>
    </StyledFilter>
  );
};

const StyledFilter = styled.div`
  .filter {
    display: flex;
    flex-direction: row;
    align-items: baseline;
    margin-bottom: 0.6rem;
  }
  .tag {
    padding: 0.5rem 0.8rem 0.5rem 0.8rem;
    margin-right: 0.4rem;

    font-size: 0.75rem;
    color: #85858e;
    background-color: #f5f2f0;

    border-radius: 16px;
    border: solid 1px #85858e;
    cursor: pointer;
  }
  .selected {
    border: solid 1px #3f910c;
    background-color: #eff4e7;
    color: #3f910c;
  }
`;
