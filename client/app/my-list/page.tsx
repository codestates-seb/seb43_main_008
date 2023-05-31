"use client";

// import FormData from "form-data";
import Image from "next/image";
import Link from "next/link";
// import { useEffect, useRef, useState } from "react";
import { useEffect, useState } from "react";

import axiosInstance from "../axiosInstance";
import styles from "./myList.module.css";

export default function Page() {
  //--------------------------------------------------------- 상태, 상수는 여기에 설정-------------------------------------------------------------

  const [useCount, setUseCount] = useState<number>(0);
  const [contentList, setContentList] = useState<any[]>([]);

  //--------------------------------------------------------- 함수는 여기에 생성------------------------------------------------------------------

  useEffect(() => {
    const seriesID = localStorage.getItem("plastic");
    if (seriesID !== null) {
      // console.log("seriesID : ", seriesID);
      const asyncGetDta = async () => {
        // await getUseCount(seriesID);
        await getContentsList(seriesID);
      };
      asyncGetDta();
    } else {
      // console.log("시리즈 아이디가 없어요");
    }
  }, []);

  const getContentsList = async (seriesID: string) => {
    try {
      const result = await axiosInstance.get(`/series/${seriesID}/daylog`);
      // console.log("받은 배열 : ", result.data.data.pagedata);
      setContentList(result.data.data.pagedata);
      setUseCount(result.data.data.pagedata.length);
      // console.log(result.data.data.pagedata);
    } catch (err) {
      // console.log(err);
    }
  };

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

      <div style={{ marginRight: "240px", marginTop: "10px", color: "#222" }}>
        사용 횟수
      </div>
      <div className={`${styles["list-useCountBarBox"]}`}>
        <div className={`${styles["list-useCountBar"]}`}>
          <RoundUse count={useCount} />
        </div>
      </div>

      {/*카드*/}
      <div className={`${styles["list-useContentBarBox"]}`}>
        <div className={`${styles["list-useContentContainer"]}`}>
          <div
            key={useCount + 1}
            className={`${styles["List-addContentsBox"]}`}
          >
            <Link href="/series">
              <Image
                src={"/plus.svg"}
                alt={"글을 하나 더 써봐요"}
                width={50}
                height={50}
              />
            </Link>
          </div>

          {/* <div
          onClick={() => {
            moveToLoginHandle("series")
          }}
          className="plus-button">
          <BsPlusLg />
        </div>
        <div
          className={`add-series menu`}
        >
          <div className="icon" />
          <div className="text">새글쓰기</div>
        </div> */}
          {contentList.map((el, index) => {
            return (
              <div key={index} className={`${styles["List-eachContents"]}`}>
                <Image
                  className={`${styles["List-eachContentsImage"]}`}
                  src={el.contentImg}
                  alt={"각각의 이미지에요"}
                  width={300}
                  height={300}
                />
                <div className={`${styles["List-eachContentsText"]}`}>
                  {el.content}
                </div>
                <div className={`${styles["List-eachContentsDate"]}`}>
                  {el.createdAt.split("T")[0]}
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}
