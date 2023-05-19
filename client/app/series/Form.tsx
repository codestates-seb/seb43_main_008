// import { useEffect, useState } from "react";
import { useState } from "react";
import styled from "styled-components";

import SubmitButton from "./SubmitButton";
import UploadImg from "./UploadImg";
import WriteInput from "./WriteInput";

export default function Form() {
  const [photo, setPhoto] = useState(false);
  const [text, setText] = useState("");

  // const [formData, setFormData] = useState({});
  // console.log(formData, "form");

  return (
    <form>
      <SeriesWrapper>
        <UploadImg setPhoto={setPhoto} />
        <WriteInput setText={setText} />
      </SeriesWrapper>
      <SubmitButton isPhotoUploaded={photo} isTextWritten={!!text} />
    </form>
  );
}
// 사진 + 글 업로드 디테일 레이아웃
const SeriesWrapper = styled.div`
  flex: 1;
  margin-top: 10px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
  background-color: white;
  display: flex;
  align-items: center;
  flex-direction: column;
  padding: 5px 24px 30px 24px;
  border-radius: 10px;
`;
