"use client";

import axios from "axios";
// import FormData from "form-data";
import Image from "next/image";
// import { useEffect, useRef, useState } from "react";
import { useEffect, useState } from "react";

import styles from "./myList.module.css";

export default function Page() {
  //--------------------------------------------------------- 상태, 상수는 여기에 설정-------------------------------------------------------------

  const token =
    "eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjMxLCJzdWIiOiJib3Jpc0BuYXZlci5jb20iLCJpYXQiOjE2ODQ2NjcyODYsImV4cCI6MTY4NDY2OTA4Nn0.9y5yPhyzEVcbMlAqr2tEA33bXOS2F3cGtyntpVZSc7IqU4Kc5iNRTAOXPEI5TXwm";
  const [useCount, setUseCount] = useState<number>(0);
  const [contentList, setContentList] = useState<any[]>([]);

  //--------------------------------------------------------- 함수는 여기에 생성------------------------------------------------------------------

  useEffect(() => {
    const seriesID = localStorage.getItem("plastic");
    if (seriesID !== null) {
      console.log("seriesID : ", seriesID);
      const asyncGetDta = async () => {
        await getUseCount(seriesID);
        await getContentsList(seriesID);
      };
      asyncGetDta();
    } else {
      console.log("시리즈 아이디가 없어요");
    }
  }, []);

  const getUseCount = async (seriesID: string) => {
    try {
      const result = await axios.get(
        `http://ec2-3-37-46-164.ap-northeast-2.compute.amazonaws.com:8080/series/${seriesID}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      console.log("총 몇개 : ", result.data.data);
      setUseCount(result.data.data.daylogCount);
    } catch (err) {
      console.log(err);
    }
  };

  const getContentsList = async (seriesID: string) => {
    try {
      const result = await axios.get(
        `http://ec2-3-37-46-164.ap-northeast-2.compute.amazonaws.com:8080/series/${seriesID}/daylog`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      console.log("받은 배열 : ", result.data.data.data);
      setContentList(result.data.data.data);
    } catch (err) {
      console.log(err);
    }
  };

  // const gotoVote = () => {};

  const RoundUse = ({ count }: any) => {
    const elements = [];

    for (let i = count; i > 0; i--) {
      elements.push(
        <div className={`${styles["List-eachTime"]}`} key={i}>
          <span>{i}</span>
        </div>
      );
    }

    return (
      <div className={`${styles["list-useCountContainer"]}`}>{elements}</div>
    );
  };

  // const ContentsCard = ({ count }: any) => {
  //   const elements = [];

  //   for (let i = count; i > 0; i--) {
  //     elements.push(
  //       <div className={`${styles.ListEachTime}`} key={i}>
  //         <span>{i}</span>
  //       </div>
  //     );
  //   }

  //   return (
  //     <div className={`${styles["list-useCountContainer"]}`}>{elements}</div>
  //   );
  // };

  return (
    <div className={`${styles["list-page"]}`}>
      {/*사용 횟수*/}
      <div className={`${styles["list-useCountBarBox"]}`}>
        <div style={{ marginLeft: "10px", marginTop: "5px", color: "#222" }}>
          사용 횟수
        </div>
        <div className={`${styles["list-useCountBar"]}`}>
          <RoundUse count={useCount} />
        </div>
      </div>

      {/*카드*/}
      <div className={`${styles["list-useContentBarBox"]}`}>
        <div className={`${styles["list-useContentContainer"]}`}>
          {contentList.map((el, index) => {
            return (
              <div key={index} className={`${styles["List-eachContents"]}`}>
                {/* <Image
                  className={`${styles.ListEachContentsImage}`}
                  src={el.contentImg}
                  alt={"각각의 이미지에요"}
                  width={250}
                  height={200}
                /> */}
                <div className={`${styles["List-eachContentsText"]}`}>
                  {el.content}
                </div>
                <div className={`${styles["List-eachContentsDate"]}`}>
                  {el.createdAt.split("T")[0]}
                </div>
              </div>
            );
          })}
          <div
            key={useCount + 1}
            className={`${styles["List-addContentsBox"]}`}
          >
            <Image
              src={"/plus.svg"}
              alt={"글을 하나 더 써봐요"}
              width={50}
              height={50}
            />
          </div>
        </div>
      </div>
    </div>
  );
}
