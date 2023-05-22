"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import styled from "styled-components";

import { GetMain } from "../api/mainApi"
import Card from "./Card";
import { Filter } from './Filter';
import { Scroll } from "./Scroll";

export const Lists: React.FC = () => {
  const [list, setList] = useState([]);
  const [pageQuery, setPageQuery] = useState<number>(1);
  const [lastDataLength, setLastDataLength] = useState<number>(0);
  const [filter, setFilter] = useState<string>("newest");

  // 문제: 최초 렌더링시 api 호출 두번됨
  // 페이지네이션: 데이터를 적재한다. 
  useEffect(() => {
    GetMain(pageQuery, filter).then((data) => {
      if (data) {
        setList((prevList) => [...prevList, ...data]);
        setLastDataLength(data.length);
      }
    });
  }, [pageQuery]);

  // 필터링: 데이터를 초기화하고 새로 불러온다. 
  useEffect(() => {
    GetMain(pageQuery, filter).then((data) => {
      if (data) {
        setList(data);
        setLastDataLength(data.length);
      }
    });
  }, [filter]);

  // 사용자의 로그인 여부를 확인하기 위한 함수 & 로그인 여부에 따라 경로를 다르게 보냄
  const router = useRouter();
  const moveHandler = (id: number, title: string) => {
    if (localStorage.getItem("Authorization")) {
      // 📌(수정 필요) 로그인 되었는지 확인
      router.push(`/detail/${id}`);
      sessionStorage.setItem("header", title);
      sessionStorage.setItem("menu", null); //📌 확인하기
    } else router.push("/login");
  };
  console.log(list)
  return (
    <StyledLists className="list">
      <Filter filter={filter} setFilter={setFilter} />
      {/* {list.map((data) => (
        data.seriesStatus !== "SERIES_ACTIVE" ? (
          <div
            onClick={() => moveHandler(data.id, data.title)}
            className="item"
            key={data.id}
          >
            <Card key={data.id} {...data} />
          </div>
        ) : null
      ))} */}

      {list.map((data) => (
        <div
          onClick={() => moveHandler(data.id, data.title)}
          className="item"
          key={`main ${data.id}`}
        >
          <Card {...data} />
        </div>

      ))}

      {/* 이전에 받아온 데이터가 12개 미만이라면 무한 스크롤 차단 */}
      {lastDataLength >= 12 && (
        <Scroll
          lastDataLength={lastDataLength}
          setPageQuery={setPageQuery}
          pageQuery={pageQuery}
          countNumber={1}
        />
      )}
    </StyledLists>
  );
};

const StyledLists = styled.section`
  a {
    color: inherit;
    text-decoration: none;
  }
  .list {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .item {
    cursor: pointer;
    display: flex;
    justify-content: center;
    width: 100%;
  }
`;
