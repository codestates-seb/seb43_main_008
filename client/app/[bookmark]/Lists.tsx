"use client";
export const dynamic = 'force-dynamic'
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import styled from "styled-components";

import { GetPageBookmark } from "../api/bookmarkApi"
import Card from "./Card";
import { Scroll } from "./Scroll";

export const Lists: React.FC = () => {
  const [list, setList] = useState([]);
  const [pageQuery, setPageQuery] = useState<number>(1);
  const [lastDataLength, setLastDataLength] = useState<number>(0);


  useEffect(() => {
    GetPageBookmark(pageQuery).then((data) => {
      if (data !== null) {
        setList((prevList) => [...prevList, ...data]); // ...list, data로 바꿔야함
        setLastDataLength(data.length);
        console.log(data)
      }
    });
  }, [pageQuery]);

  // 사용자의 로그인 여부를 확인하기 위한 함수 & 로그인 여부에 따라 경로를 다르게 보냄
  const router = useRouter();
  const moveHandler = (id: number, title: string) => {
    router.push(`/detail/${id}`);
    sessionStorage.setItem("header", title);
    sessionStorage.setItem("menu", null);
  };
  return (
    <StyledLists className="list">
      {list.length > 0 ? (
        list.map((data) => (
          <div
            onClick={() => moveHandler(data.seriseId, data.title)}
            className="item"
            key={data.id}
          >
            <Card key={data.id} {...data} />
          </div>
        ))
      ) : (
        <div className='empty-box'>북마크함이 비어있습니다.</div>
      )}
      {/* api 호출중이거나 이전에 받아온 데이터가 12개 미만이라면 무한 스크롤 차단 */}
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
    display: flex;
    justify-content: center;
    width: 100%;
  }
  .empty-box {
    text-align: center;
  }
`;
