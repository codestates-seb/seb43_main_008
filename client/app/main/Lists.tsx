"use client";

import { useRouter, useSearchParams } from 'next/navigation'
import { useEffect, useState } from 'react';
import styled from "styled-components";

import { GetMain } from '../api/mainApi';
import Card from "./Card";
import { Scroll } from './Scroll';

export const Lists = () => {
  const [list, setList] = useState([]);

  const searchParams = useSearchParams();
  const search = searchParams.get('search');
  console.log(search)

  useEffect(() => {
    GetMain().then((data) => {
      if (data) {
        setList(data)
      }
    })
  }, [])


  // 사용자의 로그인 여부를 확인하기 위한 함수 & 로그인 여부에 따라 경로를 다르게 보냄
  const router = useRouter();
  const moveHandler = () => {
    if (localStorage.getItem("accessToken")) { // 📌(수정 필요) 로그인 되었는지 확인
      router.push("/detail")
    }
    else router.push("/login")
  }


  return (
    <StyledLists className="list">
      {list.map((data) => (
        <div onClick={() => moveHandler()} className="item" key={data.id}>
          <Card key={data.id} {...data} />
        </div>
      ))}
      <Scroll />
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