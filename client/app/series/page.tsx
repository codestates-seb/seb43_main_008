"use client";

import FormData from "form-data";
import Image from "next/image";
import { useRouter } from "next/navigation";
import { useEffect, useRef, useState } from "react";

import axiosInstance from "../axiosInstance";
import styles from "./series.module.css";

export default function Page() {
  //---------------------------------------------------------상태, 상수는 아래 여기에 설정---------------------------------------------------------------------

  const [imageFile, setImageFile] = useState<any>(""); // 이미지 파일을 담는 상태
  const [userText, setUserText] = useState<any>(""); // 텍스트를 담는 상태
  const [isOpen, setIsOpen] = useState<boolean>(false); // 모달창 오픈
  const fileRef = useRef<HTMLInputElement>(null); // 이미지 input을 참고하는 ref 객체
  const showRef = useRef<any>(null); // 업로드한 이미지를 보여주는 Image 태그를 참조하는 Ref 객체
  const buttonRef = useRef<any>(null);
  //---------------------------------------------------------콘솔 테스트 중----------------------------------------------------------------------------------

  // useEffect(() => {
  //   console.log("하아이이이이");
  // }, []);

  //---------------------------------------------------------함수는 아래 여기에 생성--------------------------------------------------------------------------
  // useEffect(() => {
  //   if (imageFile === "" || userText() === "") {
  //     buttonRef.current.disabled = true;
  //   } else {
  //     buttonRef.current.disabled = false;
  //   }
  // }, [imageFile, userText]);

  useEffect(() => {
    if (imageFile === "" || userText.trim() === "") {
      buttonRef.current.disabled = true;
    } else {
      buttonRef.current.disabled = false;
    }
  }, [imageFile, userText]);

  const typingContent = (e: any) => {
    setUserText(e.target.value);
  };

  const handleClick = () => {
    fileRef?.current?.click();
  };

  const handleChange = (e: any) => {
    setImageFile(e.target.files[0]);
    // const targetFiles = URL.createObjectURL(e.target.files[0]);
    if (showRef.current !== null) {
      showRef.current.src = URL.createObjectURL(e.target.files[0]);
    }
  };

  const openChange = () => {
    setIsOpen(true);
  };

  return (
    <div className={`${styles["series-body"]}`}>
      {isOpen === true ? (
        <Modal
          imageFile={imageFile}
          userText={userText}
          setIsOpen={setIsOpen}
        />
      ) : null}

      <div className={`${styles["series-bodyBox"]}`}>
        {/*------------------------------------------------------이미지 인풋------------------------------------------------------*/}
        <form className={`${styles["series-imageBox"]}`}>
          <input
            ref={fileRef}
            hidden
            type="file"
            accept="image/*"
            onChange={handleChange}
          />
          <div style={{ marginTop: "50px", marginBottom: "130px" }}>
            <Image
              className={`${styles["series-imageShower"]}`}
              ref={showRef}
              src={"/image.svg"}
              alt={"이미지 업로드"}
              width={275}
              height={100}
              onClick={handleClick}
            />
          </div>
        </form>
        {/*------------------------------------------------------텍스트 박스------------------------------------------------------*/}
        <textarea
          onChange={typingContent}
          className={`${styles["series-input"]}`}
          placeholder="노 모어 플라스틱~&#10;시리즈를 작성해 주세요."
          rows={10}
          cols={10}
          wrap="hard"
        />
      </div>
      <button
        className={`${styles["series-button"]}`}
        ref={buttonRef}
        onClick={openChange}
        style={
          imageFile !== "" && userText.trim() !== ""
            ? {
                backgroundColor: "#eff4e7",
                color: "#3f910c",
                border: "solid 1px #3f910c",
              }
            : {}
        }
      >
        작성완료
      </button>
    </div>
  );
}

//---------------------------------------------------------- 아래는 모달 컴포넌트------------------------------------------------------

const Modal = ({ imageFile, userText, setIsOpen }: any) => {
  const [check, setCheck] = useState(false); // 확인 체크를 담아 놓는 상태
  const [agree, setAgree] = useState(false); // 동의 체크를 담아 놓는 상태
  const ModalButton = useRef<any>(null); // 모달의 확인 버튼
  const router = useRouter();

  useEffect(() => {
    if (ModalButton.current !== null) {
      if (check === false || agree === false) {
        ModalButton.current.disabled = true;
      } else {
        ModalButton.current.disabled = false;
      }
    }
  }, [check, agree]);

  const clickCheck = () => {
    setCheck(!check);
  };

  const clickAgree = () => {
    setAgree(!agree);
  };

  const closeModal = () => {
    setIsOpen(false);
  };

  // 리스트 페이지로 이동합시다.
  const nextStep = async () => {
    await sendData();
    router.push("/my-list");
  };

  const sendData = async () => {
    console.log("받은 이미지", imageFile);

    let formData = new FormData();

    formData.append("image", imageFile);
    formData.append("content", userText);
    let id = localStorage.getItem("plastic");
    try {
      const result = await axiosInstance.post(
        `/series/${id}/daylog`,
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
      console.log("요청 성공", result.data.series.id);
    } catch (err) {
      console.log("에러 발생", err);
      return false;
    }
    return true;
  };

  return (
    <div className={`${styles["series-modalBody"]}`}>
      <div className={`${styles["series-modalHeader"]}`}>
        <Image
          src={"/close.svg"}
          width={13}
          height={13}
          alt={"취소버튼"}
          onClick={closeModal}
        />
      </div>
      <div
        style={{ marginBottom: "28px", marginLeft: "25px", color: "#3f910c" }}
      >
        아래 안내 내용을 확인해 주세요!
      </div>
      <div style={{ fontSize: "14px" }}>
        작성된 시리즈는 수정이나 삭제를 할 수 없습니다.
      </div>

      <div style={{ marginTop: 20, marginBottom: 20 }}>
        <div>
          <input
            type="checkbox"
            id="findCheck"
            name="findCheck"
            onChange={clickCheck}
          />
          <label
            htmlFor="findCheck"
            style={{ marginLeft: 10, marginBottom: 40, fontSize: "14px" }}
          >
            안내 내용을 확인하였습니다.
          </label>
        </div>
        <div>
          <input
            type="checkbox"
            id="agree"
            name="agree"
            onChange={clickAgree}
          />
          <label
            htmlFor="agree"
            style={{ marginLeft: 10, marginBottom: 10, fontSize: "14px" }}
          >
            안내에 동의 합니다.
          </label>
        </div>
      </div>

      <div style={{ fontSize: "14px", color: "#7d7d83 " }}>
        체크 박스 두개에 다 체크해야{" "}
      </div>
      <div style={{ fontSize: "14px", color: "#7d7d83 " }}>
        확인 버튼을 누를 수 있습니다!
      </div>
      <button
        className={`${styles["series-modalButton"]}`}
        ref={ModalButton}
        onClick={nextStep}
        style={
          check && agree
            ? {
                backgroundColor: "#eff4e7",
                color: "#3f910c",
                border: "solid 1px #3f910c",
              }
            : {}
        }
      >
        확인
      </button>
    </div>
  );
};
