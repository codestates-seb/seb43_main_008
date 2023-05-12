"use client";

import { useState } from "react";
import styled from "styled-components";

export const Filter: React.FC = () => {
  const [isSelected, setIsSelected] = useState(false);

  return (
    <StyledFilter>
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