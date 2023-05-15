"use client";

import React from "react";
import styled from "styled-components";

const CountCardContainer = styled.div`
  flex: 1;
  color: #222;
  background-color: white;
  display: flex;
  align-items: center;
  flex-direction: column;
  padding: 5px;
  border-radius: 10px;
  align-items: flex-start;
`;
const GetBadgeText = styled.div`
  /* all: unset; */
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 5px;
  margin-left: 7px;
`;

const PlasticList = styled.div`
  border: 1px solid #9b9ba0;
  padding: 5px;
  border-radius: 5px;
  background-color: white;
  display: flex;
  align-items: center;
  justify-content: space-between;
  /* position: sticky; */
  width: 100%;
  flex-direction: column;
  align-items: flex-start;

  .list-wrapper {
    margin-top: 300px;
    list-style: none;
    margin: auto;
    /* padding: 0; */
    display: flex;
    gap: 5px;
  }

  .my-plastic {
    display: flex;
    align-items: center;
    margin: 5px 0;
    justify-content: space-between;
    flex-direction: column;

    & > * + * {
      margin-left: 10px;
    }
  }

  .plastic-circle {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    background-color: #fff8de;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
  }
`;

export default function BadgeDetail() {
  return (
    <>
      <CountCardContainer>
        <GetBadgeText>획득한 뱃지 List</GetBadgeText>
        <PlasticList>
          <ul className="list-wrapper">
            <li className="my-plastic">
              <div className="plastic-circle">😀</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">😆</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">😉</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">😍</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">🍓</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">😝</div>
            </li>
          </ul>
          <ul className="list-wrapper">
            <li className="my-plastic">
              <div className="plastic-circle">😀</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">😆</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">😉</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">😍</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">🍓</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">😝</div>
            </li>
          </ul>
          <ul className="list-wrapper">
            <li className="my-plastic">
              <div className="plastic-circle">😀</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">😆</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">😉</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">😍</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">🍓</div>
            </li>

            <li className="my-plastic">
              <div className="plastic-circle">😝</div>
            </li>
          </ul>
        </PlasticList>
      </CountCardContainer>
    </>
  );
}
