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
  padding: 30px 24px;
  border-radius: 10px;
`;

const PlasticList = styled.div`
  border: 1px solid #9b9ba0;
  padding: 5px;
  border-radius: 5px;
  background-color: white;
  display: flex;
  align-items: center;
  justify-content: space-between;
  position: sticky;
  top: 84px;
  width: 100%;

  .title {
    font-size: 20px;
    font-weight: bold;
    margin-bottom: 10px;
  }

  .list-wrapper {
    margin-top: 30px;
    list-style: none;
    margin: 0 auto;
    padding: 0;
    display: flex;
  }

  .list-detail-wrapper {
    margin-left: 75px;
    margin-bottom: 5px;
  }

  .my-plastic {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
  }

  .plastic-circle {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    background-color: #fff8de;
    margin-right: 12px;

    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
  }
`;

export default function CountCard() {
  return (
    <CountCardContainer>
      <PlasticList>
        <div>
          <div className="title">사용 횟수</div>
          <ul className="list-wrapper">
            <div className="list-detail-wrapper">
              <li className="my-plastic">
                <div className="plastic-circle">1회</div>
              </li>
            </div>
            <div className="list-detail-wrapper">
              <li className="my-plastic">
                <div className="plastic-circle">2회</div>
              </li>
            </div>
            <div className="list-detail-wrapper">
              <li className="my-plastic">
                <div className="plastic-circle">3회</div>
              </li>
            </div>
            <div className="list-detail-wrapper">
              <li className="my-plastic">
                <div className="plastic-circle">4회</div>
              </li>
            </div>
            <div className="list-detail-wrapper">
              <li className="my-plastic">
                <div className="plastic-circle">5회</div>
              </li>
            </div>
            <div className="list-detail-wrapper">
              <li className="my-plastic">
                <div className="plastic-circle">6회</div>
              </li>
            </div>
            <div className="list-detail-wrapper">
              <li className="my-plastic">
                <div className="plastic-circle">7회</div>
              </li>
            </div>
          </ul>
        </div>
      </PlasticList>
    </CountCardContainer>
  );
}
