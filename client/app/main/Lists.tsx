"use client";

import Link from 'next/link';
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

  return (
    <StyledLists className="list">
      {list.map((data) => (
        <Link href="/detail" className="item" key={data.id}>
          <Card key={data.id} {...data} />
        </Link>
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