"use client";

// import axios from "axios"
import { useState } from "react";
import styled from "styled-components";
// import { useSWRConfig } from 'swr'

export const Filter: React.FC = () => {
  const [filter, setFilter] = useState<string>("newest");

  /*
    const { mutate } = useSWRConfig()


  // filter 값이 변경될 때, 데이터를 다시 요청하기 위해 mutate 함수 호출
  useEffect(() => {
    const fetchData = async () => {
      try {
        // 서버에 새로운 데이터를 요청하고 필터링된 데이터를 가져온다
        const response = await axios.get(`${process.env.NEXT_PUBLIC_API_URL}/series?tab=${filter}`);
        const newData = response.data;

        // 필터링된 데이터로 캐시를 갱신한다
        mutate('/series', newData, false);
      } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
      }
    };

    fetchData();
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

  )
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
`