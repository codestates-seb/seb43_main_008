"use client";

import { useState } from "react";
import styled from "styled-components";

import Card from "./Card";
import { Header } from "./Header";
import list from "./list";

export default function Main() {
  const [isSelected, setIsSelected] = useState(false);
  return (
    <StyledMain>
      <Header />
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
          <li className="item" key={data.id}>
            <Card key={data.id} {...data} />
          </li>
        ))}
      </section>
    </StyledMain>
  );
}

const StyledMain = styled.main`
  width: 100%;
  min-width: 390px;

  display: flex;
  flex-direction: column;

  position: relative;
  /* top: 67.227px; */
  padding: 0 0.5rem 1.6rem 0.5rem;
  color: #222;

  .filter {
    display: flex;
    flex-direction: row;
    align-items: baseline;
    margin-left: 0.8rem;
    margin-bottom: 0.6rem;

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
  }

  .list {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    /* margin: 0;
    padding: 0; */
  }

  .item {
    display: flex;
    justify-content: center;
    width: 100%;
    /* margin: 0;
    padding: 0; */
  }

  @media screen and (min-width: 768px) {
    .list {
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      flex-wrap: wrap;
    }
    .item {
      width: 50%;
    }
    .title {
      font-size: 2.64rem;
      margin-bottom: 1.75rem;
    }
    .filter {
      margin-bottom: 1.5rem;
      .tag {
        padding: 0.8rem 1.28rem;
        margin-right: 0.64rem;

        font-size: 1.15rem;
        border-radius: 21px;
      }
    }
  }
`;
