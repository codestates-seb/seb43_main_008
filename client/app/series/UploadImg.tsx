"use client";
import Image from "next/image";
import styled from "styled-components";

function PictureLabel() {
  // const [sendPic, setSendPic]

  return (
    <label
      htmlFor="picture"
      style={{
        display: "flex",
        alignItems: "center",
        justifyContent: "center;",
      }}
    >
      <Image
        src="/add-pic.svg"
        alt="사진 업로드 아이콘"
        width="200"
        height="200"
        style={{ cursor: "pointer;" }}
      />
    </label>
  );
}

export default function UploadImg() {
  return (
    <>
      <UploadImageContainer>
        {/* 파일 중에서 사진만 업로드 가능 */}
        <PictureLabel />
        <input
          type="file"
          accept="image/*"
          id="picture"
          style={{ display: "none" }}
        />
      </UploadImageContainer>
    </>
  );
}

const UploadImageContainer = styled.div`
  background-color: white;
  margin-top: 10px;
  margin-bottom: 40px;
  width: 100%;
  height: 60vw;
  display: flex;
  align-items: center;
  padding: 5px;
  flex-direction: column;
  border-radius: 10px;
  border: 1px solid #9b9ba0;
`;
