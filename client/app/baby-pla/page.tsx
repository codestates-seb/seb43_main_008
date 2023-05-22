"use client";

import axios from "axios";
import Image from "next/image";
import { useRouter } from "next/navigation";
// import Router from "next/router";
import { useEffect, useRef, useState } from "react";
import { AiOutlineLeft } from "react-icons/ai";
import { GrLock, GrUnlock } from "react-icons/gr";

import styles from "./babyPla.module.css";
export default function Page() {
  //--------------------------------------------------------- 상태, 상수는 여기에 설정---------------------------------
  const [title, setTitle] = useState<string>(""); // 초기값 빈문자열의 플라스틱 이름 인풋 상태 관리
  const buttonRef = useRef<HTMLButtonElement>(null);
  const [isPublic, setIsPublic] = useState<boolean>(true);
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const router = useRouter();
  //--------------------------------------------------------- 함수는 여기에 생성--------------------------------------

  // 아래는 플라스틱 이름 인풋 관련 함수
  useEffect(() => {
    if (title.length === 0 && buttonRef.current !== null) {
      buttonRef.current.disabled = true;
    } else if (title.length > 0 && buttonRef.current !== null) {
      buttonRef.current.disabled = false;
    }
  }, [title]);

  const sendData = async () => {
    try {
      const result = await axios.post(
        `http://ec2-3-37-46-164.ap-northeast-2.compute.amazonaws.com:8080/series?public=${isPublic}`,
        { title: title },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization:
              "Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjI2LCJzdWIiOiJhbDAwZmhhQG5hdmVyLmNvbSIsImlhdCI6MTY4NDU4NDIxOSwiZXhwIjoxNjg0NTg2MDE5fQ.KUxmnc--qVV3ic9fudNTSTzamamXtXBNQjaHYSCEdbmsa5ijKKkVJVhMKUy_M-EL",
          },
        }
      );
      console.log(result.data.data.id);
      localStorage.setItem("plastic", result.data.data.id);
    } catch (err) {
      console.log(err);
      return false;
    }
    return true;
  };

  // 아래는 플라스틱 이름 title 상태 업데이트 관련 함수
  const typingInput = (e: any) => {
    setTitle(e.target.value);
    console.log(e.target.value);
  };

  const openModal = () => {
    setIsOpen(true);
  };

  // const closeModalOutSide = () => {
  //   setIsOpen(false);
  // };

  const nestStep = async () => {
    console.log(title, isPublic);
    let result = await sendData();
    console.log(result);
    if (result === false) {
      console.log("Error");
    } else {
      router.push("/series");
    }
  };

  return (
    <div className={`${styles["start-page"]}`}>
      {isOpen === true ? (
        <Modal
          isPublic={isPublic}
          setIsPublic={setIsPublic}
          setIsOpen={setIsOpen}
        />
      ) : null}

      {/*----------------------------------------------헤더----------------------------------------------*/}
      <div className={`${styles["start-header"]}`}>
        <div>
          <AiOutlineLeft size="18" cursor="pointer" />
        </div>
        <div className={`${styles["start-headerText"]}`}>
          플라스틱 육아 시작하기
        </div>
        <div
          className={`${styles["startHeader-lockIconBox"]}`}
          onClick={openModal}
        >
          {isPublic ? <GrUnlock /> : <GrLock />}
          <div style={{ fontSize: "10px", marginTop: "2px" }}>
            {isPublic === true ? "공개 중" : "비공개 중"}
          </div>
        </div>
      </div>

      {/*----------------------------------------------로고와 입력창----------------------------------------------*/}
      <div className={`${styles["start-body"]}`}>
        <Image
          className={`${styles["start-logoImage"]}`}
          src={"/logo.png"}
          alt={"로고 입니다"}
          width={200}
          height={40}
        />
        <div className={`${styles["start-textBox"]}`}>
          <span>육아를 시작하기에 앞서</span>
          <span>플라스틱의 이름을 설정해 주세요!</span>
        </div>
        <div className={`${styles["start-inputBox"]}`}>
          <input
            onChange={typingInput}
            className={`${styles["start-input"]}`}
            type="text"
            placeholder="귀여운 재활용품의 이름을 정해주세요❤️"
          />
          {/*----------------------------------------------아래는 입양 완료 버튼----------------------------------------------*/}
          <button
            onClick={nestStep}
            ref={buttonRef}
            className={`${styles["start-button"]}`}
            disabled
            style={
              title.length > 0
                ? {
                    backgroundColor: "#eff4e7",
                    color: "#3f910c",
                    border: "solid 1px #3f910c",
                  }
                : {}
            }
          >
            육아 시작
          </button>
        </div>
      </div>
    </div>
  );
}

//--------------------------아래는 모달창 컴포넌트 - React-modal 라이브러리는 현재 13에서 쓸 수 없음---------------------------------------------------------
const Modal = ({ isPublic, setIsPublic, setIsOpen }: any) => {
  const [word, setWord] = useState("");

  useEffect(() => {
    if (isPublic === true) {
      setWord("공개");
    } else {
      setWord("비공개");
    }
  }, [isPublic]);

  const setPublic = () => {
    setIsPublic(true);
  };

  const setPrivate = () => {
    setIsPublic(false);
  };

  const CloseModal = () => {
    setIsOpen(false);
  };

  return (
    <div className={`${styles["start-modalBody"]}`}>
      <div style={{ fontWeight: "bolder", marginBottom: 20 }}>
        작성한 시리즈의 공개 여부를 선택해주세요.
      </div>
      <div style={{ fontWeight: "bolder", marginBottom: 10 }}>
        <input
          onChange={setPublic}
          type="radio"
          name="pub"
          checked={isPublic}
        />
        <label style={{ marginLeft: 10, marginRight: 30 }}>공개</label>
        <input onChange={setPrivate} type="radio" name="pub" />
        <label style={{ marginLeft: 10 }}>비공개</label>{" "}
        {/*이름이 같아야 하나만 선택이 된다.*/}
      </div>
      <div style={{ fontWeight: "bolder", marginBottom: 40 }}>
        <span style={{ color: "#3f910c" }}>{word}</span>
        <span>로 설정하였습니다</span>
      </div>
      <div className={`${styles["start-modalTextBox"]}`}>
        <div>공개로 시리즈를 작성해주시면,</div>
        <div>실천가들과 더 많은 이야기를 함께 나눌 수 있어요~</div>
      </div>
      <button className={`${styles["start-modalButton"]}`} onClick={CloseModal}>
        확인
      </button>
    </div>
  );
};
