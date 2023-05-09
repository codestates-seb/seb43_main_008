"use client";

// import React from "react";
// import React, { useState } from "react";
// import styles from "/series.module.css";
import TextareaAutosize from "react-textarea-autosize";
import styled from "styled-components";

// import textarea from "./textarea";

//////////////////
function PictureLabel() {
  return (
    <label htmlFor="picture" style={{ display: "flex", alignItems: "center" }}>
      <img
        src="/add-pic.svg"
        alt="사진 추가 아이콘"
        style={{
          width: "1200px",
          height: "500px",
          color: "#757575",
          cursor: "pointer",
        }}
      />
    </label>
  );
}
//////////////////

const MainSeriesContainer = styled.div`
  padding-top: 54px;
  max-width: 1024px;
  margin: 0 auto;
  background-color: #fafafa;
  min-height: 100vh;

  /* @media screen and (min-width: 768px) {
    .list {
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      flex-wrap: wrap;
    }
  } */
`;

const SeriesWrapper = styled.div`
  max-width: 1000px;
  margin: 0 auto;
  padding: 30px;
`;

const SeriesList = styled(SeriesWrapper)`
  max-width: 729px;
`;

const InputContainer = styled.div`
  flex: 1;
  color: #222;
  background-color: white;
  display: flex;
  align-items: center;
  padding: 20px 24px;
  border: 1px solid #9b9ba0;
  border-radius: 10px;
`;

const WriteInput = styled(TextareaAutosize)`
  font-size: 12px;
  width: 100%;
  color: #9b9ba0;
  border: none;
  padding: 10px;
  overflow: hidden;
  resize: none;
  &:focus {
    outline: none;
  }
`;

const SubmitButton = styled.button`
  background-color: #fcfcfd;
  color: #36395a;
  border: 1px solid rgba(34, 36, 38, 0.5);
  padding: 0.6rem 1.5rem;
  margin-left: 15px;
  border-radius: 3px;
  cursor: pointer;

  &:hover {
    transform: translateY(-2px);
  }
`;

const UploadImageContainer = styled.div`
  background-color: white;
  margin-top: 25px;
  margin-bottom: 25px;
  display: flex;
  align-items: center;
  padding: 20px 24px;
  flex-direction: column;
  border-radius: 10px;
  border: 1px solid #9b9ba0;
`;

const PlasticList = styled.div`
  /* max-width: 150px; */
  border: 1px solid #9b9ba0;
  padding: 5px;
  border-radius: 5px;
  background-color: white;
  display: flex;
  align-items: center;
  justify-content: space-between;
  /* position: sticky; */
  /* top: 84px; */

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

  .my-plastic {
    display: flex;
    align-items: center;
    margin-bottom: 10px;

    .plastic-img {
      width: 40px;
      height: 40px;
      border-radius: 40px;
      background-color: #fff8de;
      margin-right: 12px;
    }

    .nickname {
      font-size: 15px;
      color: #757575;
    }
  }
`;
/////////////////////////////////////////////

export default function page() {
  //   const [text, setText] = useState<string>("");

  //   const handleChange = (event) => {
  //     setText(event.target.value);
  //   };

  return (
    <>
      <MainSeriesContainer>
        <h1>시리즈를 작성해주세요</h1>
        <SeriesWrapper>
          <SeriesList>
            {/* /////////////////////////////////////////// */}
            <PlasticList>
              <div>
                <div className="title">육아 명단</div>
                <ul className="list-wrapper">
                  <li className="my-plastic">
                    <div className="plastic-img" />
                    <div
                      className="nickname txt-bold"
                      style={{ marginRight: "10px;" }}
                    >
                      아가봉다리
                    </div>
                  </li>
                  <li className="my-plastic">
                    <div className="plastic-img" />
                    <div
                      className="nickname txt-bold"
                      style={{ marginRight: "10px;" }}
                    >
                      도도봉다리
                    </div>
                  </li>
                  <li className="my-plastic">
                    <div className="plastic-img" />
                    <div
                      className="nickname txt-bold"
                      style={{ marginRight: "10px;" }}
                    >
                      아름봉다리
                    </div>
                  </li>
                  <li className="my-plastic">
                    <div className="plastic-img" />
                    <div
                      className="nickname txt-bold"
                      style={{ marginRight: "10px;" }}
                    >
                      지인봉다리
                    </div>
                  </li>
                  <li className="my-plastic">
                    <div className="plastic-img" />
                    <div
                      className="nickname txt-bold"
                      style={{ marginRight: "10px;" }}
                    >
                      순현봉다리
                    </div>
                  </li>
                </ul>
              </div>
            </PlasticList>
            {/* /////////////////////////////////////////// */}
            <UploadImageContainer>
              {/* ///////////////////// */}
              <PictureLabel />
              <input type="file" id="picture" style={{ display: "none" }} />
              {/* ///////////////////// */}
            </UploadImageContainer>
            <InputContainer>
              <WriteInput
              // value={text}
              // onChange={handleChange}
              // placeholder="노 모어 플라스틱, 더 클린 서울"
              // style={{ fontSize: "20px;" }}
              />
              <SubmitButton type="submit">ok</SubmitButton>
            </InputContainer>
          </SeriesList>
        </SeriesWrapper>
      </MainSeriesContainer>
    </>
  );
}
