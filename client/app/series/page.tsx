"use client";

import styled from "styled-components";

// import { Axios } from "axios";
import SubmitButton from "./SubmitButton";
import UploadImg from "./UploadImg";
import WriteInput from "./WriteInput";

export default function page() {
  // // const [value, setValue] = useState("");
  // // const handleChildValue = (childValue) => {
  // //   // 자식 컴포넌트에서 전달된 값을 처리
  // //   setValue(childValue);
  // // };
  // // onChildValue(event.target.value);

  return (
    <>
      <MainSeriesContainer>
        <SeriesWrapper>
          <UploadImg />
          {/* <WriteInput onChildValue={handleChildValue} /> */}
          <WriteInput />
        </SeriesWrapper>
        <SubmitButton />
      </MainSeriesContainer>
    </>
  );
}
// 페이지 기본 레이아웃
const MainSeriesContainer = styled.div`
  box-sizing: border-box;
  padding: 1px 24px;
  max-width: 1024px;
  margin: 0 auto;
  background-color: #fafafa;
  min-height: 550px;
  min-width: 320px;

  /* @media screen and (min-width: 768px) { */
  @media screen and (max-width: 768px) {
  }
`;

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

// 사진 -> 처리 방법
// 이미지 파일 불러오면 중앙에 미리보기
// 사이즈 조절 어떻게? -> 사용자가? 내가?
// 커서 조금 정리?
// 이미지랑 글 한글자라도 입력되어야 작성 완료 버튼 활성화
// 미묘하게 위치가 안맞아서 킹받는 체크 박스 정리하기
// 확인 버튼 에러?
