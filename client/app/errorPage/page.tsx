"use client";
import Image from "next/image";
import React from "react";
import styled from "styled-components";

const ErrorPageContainer = styled.div`
  padding: 1px 24px;
  max-width: 1024px;
  margin: 0 auto;
  background-color: #000000 rgb(000);
  min-height: 90vh;
  min-width: 368px;

  @media screen and (max-width: 768px) {
  }
`;
export default function page() {
  return (
    <>
      <ErrorPageContainer>
        <Image
          src="/images/star.png"
          alt="별"
          width="190"
          height="300"
          // style={}
        />
        <Image src="/images/mountain.png" alt="산" width="190" height="300" />
        <Image src="/images/ufo.png" alt="우주접시" width="190" height="300" />
        <path
          id="UFO-beam"
          d="M1143.5 497L1509 1474H1143.5H778L1143.5 497Z"
          fill="#9670BA"
        />
      </ErrorPageContainer>
    </>
  );
}

// "use client";

// import moment from "moment";
// import { useState, FC } from "react";
// import styled from "styled-components";

// // import { Axios } from "axios";
// import Navbar from "../../Navbar";
// import Header from "./Header";
// import UploadImg from "./UploadImg";
// import Information from "./WarningInformation";
// import WriteInput from "./WriteInput";

// // 페이지 기본 레이아웃
// const MainSeriesContainer = styled.div`
//   padding: 1px 24px;
//   max-width: 1024px;
//   margin: 0 auto;
//   background-color: #fafafa;
//   min-height: 100vh;
//   min-width: 368px;

//   /* @media screen and (min-width: 768px) { */
//   @media screen and (max-width: 768px) {
//   }
// `;

// // 사진 + 글 업로드 디테일 레이아웃
// const SeriesWrapper = styled.div`
//   flex: 1;
//   margin-top: 10px;
//   box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
//   background-color: white;
//   display: flex;
//   align-items: center;
//   flex-direction: column;
//   padding: 5px 24px 30px 24px;
//   border-radius: 10px;
// `;

// const SubmitButton = styled.button`
//   background-color: #fcfcfd;
//   color: #222;
//   border: 1px solid rgba(34, 36, 38, 0.5);
//   padding: 0.6rem 0.6rem;
//   margin-left: 10px;
//   border-radius: 3px;
//   margin-top: 20px;
//   cursor: pointer;

//   &:active {
//     transform: translateY(-2px);
//   }
// `;

// export default function page() {
//   // 수정, 삭제 불가 경고 안내 state
//   const [showInformation, setShowInformation] = useState(false);

//   // const [value, setValue] = useState("");
//   // const handleChildValue = (childValue) => {
//   // 자식 컴포넌트에서 전달된 값을 처리
//   //   setValue(childValue);
//   // };
//   // onChildValue(event.target.value);

//   // 헤더에서 저장 버튼 누르면 "YYYY년 MM월 DD일" 형식으로 날짜 저장 ////////////////
//   const [date, setDate] = useState("");
//   const now = moment();
//   // console.log(now.format("YYYY년 MM월 DD일"), "시간");

//   const handleCreated = () => {
//     setDate(now.format("YYYY년 MM월 DD일"));
//     // 이제 여기에 Date를 Axios로 post 하는 함수 만들어 넣자~
//     setShowInformation(true); // 작성 완료 버튼을 누르면 showInformation을 true로 설정
//   };
//   console.log(date);

//   const handleCloseModal = () => {
//     setShowInformation(false); // '확인' 버튼을 누르면 모달을 닫음
//   };

//   return (
//     <>
//       <Header
//         backButton={true}
//         textContent={"시리즈 작성"}
//         secretButton={true}
//       />
//       <MainSeriesContainer>
//         <SeriesWrapper>
//           <UploadImg />
//           <WriteInput onChildValue={handleChildValue} />
//           <WriteInput />

//           <SubmitButton
//             type="submit"
//             onClick={handleCreated}
//             style={{ marginTop: "80px" }}
//           >
//             작성 완료
//           </SubmitButton>
//         </SeriesWrapper>
//       </MainSeriesContainer>
//       {showInformation && ( // showInformation 상태에 따라 Information 컴포넌트를 렌더링
//         <Information
//           message="아래의 안내 내용을 확인해주세요❤️"
//           additionalMessage="안내 내용 좀 우리 모두 같이 정해보아용~ 데헷데헷"
//           onClose={handleCloseModal} // 확인 버튼을 누르면 호출될 함수를 prop으로 전달
//         />
//       )}

//       <Navbar />
//     </>
//   );
// }
