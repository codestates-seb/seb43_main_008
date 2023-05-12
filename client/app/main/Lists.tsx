"use client";

import Link from 'next/link';
import styled from "styled-components";

import Card from "./Card";
import list from "./list";

export const Lists = () => {
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