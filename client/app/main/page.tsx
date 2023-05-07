"use client";

import styled from "styled-components";
import { useState } from "react";
import Card from "./Card";
import list from "./list";

export default function Main() {
  const [isSelected, setIsSelected] = useState(false);
  return (
    <StyledMain>
      <h1 className="title">홈</h1>
      <div className="filter">
        <div
          className={isSelected ? "tag" : "selected tag"}
          onClick={() => setIsSelected(false)}
        >
          새로운 게시글
        </div>
        <div
          className={isSelected ? "selected tag" : "tag"}
          onClick={() => setIsSelected(true)}
        >
          인기 게시글
        </div>
      </div>
      <section className="list">
        {list.map((data) => (
          <Card key={data.id} {...data} />
        ))}
      </section>
    </StyledMain>
  );
}

const StyledMain = styled.main`
  width: 390px;

  display: flex;
  flex-direction: column;
  position: relative;
  padding: 1.6rem;

  .title {
    margin-left: 0.3rem;
    font-size: 1.4rem;
    font-weight: 900;
    margin-bottom: 1.2rem;
  }

  .filter {
    display: flex;
    flex-direction: row;
    align-items: baseline;
    margin-bottom: 0.6rem;

    .tag {
      padding: 0.5rem 0.8rem 0.5rem 0.8rem;
      margin-right: 0.4rem;
      border-radius: 16px;
      color: #85858e;
      background-color: #f5f2f0;
      border: solid 1px #85858e;
      font-size: 0.75rem;
      cursor: pointer;
    }
    .selected {
      border: solid 1px #3f910c;
      background-color: #eff4e7;
      color: #3f910c;
    }
  }

  .list {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
`;
