"use client";

// import { useRouter } from 'next/navigation'
import { useEffect, useState } from 'react';
import styled from "styled-components";

import { GetMain } from '../api/api';
import Card from "./Card";

export const Lists = () => {
  const [list, setList] = useState([]);

  useEffect(() => {
    const fetchList = async () => {
      try {
        const data = await GetMain();
        setList(data);
      } catch (error) {
        console.error('Error fetching list:', error);
      }
    };
    fetchList();
  }, []);

  /*
    const router = useRouter();

  const moveHandler = () => {
    if (localStorage.getItem("accessToken")) { // 📌(수정 필요) 로그인 되었는지 확인
      router.push("/detail")
    }
    else router.push("/login")
  }


  onClick={() => moveHandler()}
  */

  return (
    <StyledLists className="list">
      {list.map((data) => (
        <div className="item" key={data.id}>
          <Card key={data.id} {...data} />
        </div>
      ))}
    </StyledLists>
  )
}

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
`