"use client";

import styled from "styled-components";

import Form from "./Form";

export default function page() {
  return (
    <>
      <MainSeriesContainer>
        <Form />
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

// 사진 -> 처리 방법
// 이미지 파일 불러오면 중앙에 미리보기
// 사이즈 조절 어떻게? -> 사용자가? 내가?
// 커서 조금 정리?
// 이미지랑 글 한글자라도 입력되어야 작성 완료 버튼 활성화
// 미묘하게 위치가 안맞아서 킹받는 체크 박스 정리하기
// 확인 버튼 에러?
