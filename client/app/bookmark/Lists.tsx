"use client";

import styled from "styled-components";

import Card from "./Card";
import list from "./list";

export const Lists = () => {
  return (
    <StyledLists className="list">
      {list.map((data) => (
        <li className="item" key={data.id}>
          <Card key={data.id} {...data} />
        </li>
      ))}
    </StyledLists>
  )
}

const StyledLists = styled.section`
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