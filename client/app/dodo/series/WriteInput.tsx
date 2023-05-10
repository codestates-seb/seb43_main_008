"use client";

import React, { useState } from "react";
import TextareaAutosize from "react-textarea-autosize";
import styled from "styled-components";

const WriteInputDetail = styled(TextareaAutosize)`
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

export default function WriteInput() {
  const [text, setText] = useState("");

  const handleChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    setText(event.target.value);
  };

  return (
    <WriteInputDetail
      value={text}
      // onChange={(event)=>{}} -> handleChange의 event 타입 추론 (타입 빨간줄 나오면 마우스 올리기 -> 대부분 타입 알려줌)
      onChange={handleChange}
      placeholder="노 모어 플라스틱, 더 클린 서울"
      style={{ fontSize: "20px;" }}
    />
  );
}

{
  /* /////////////////////////////////////////// */
}
//   const PlasticList = styled.div`
//   /* max-width: 150px; */
//   border: 1px solid #9b9ba0;
//   padding: 5px;
//   border-radius: 5px;
//   background-color: white;
//   display: flex;
//   align-items: center;
//   justify-content: space-between;
//   /* position: sticky; */
//   /* top: 84px; */

//   .title {
//     font-size: 20px;
//     font-weight: bold;
//     margin-bottom: 10px;
//   }

//   .list-wrapper {
//     margin-top: 30px;
//     list-style: none;
//     margin: 0 auto;
//     padding: 0;
//     display: flex;
//   }

//   .my-plastic {
//     display: flex;
//     align-items: center;
//     margin-bottom: 10px;

//     .plastic-img {
//       width: 40px;
//       height: 40px;
//       border-radius: 40px;
//       background-color: #fff8de;
//       margin-right: 12px;
//     }

//     .nickname {
//       font-size: 15px;
//       color: #757575;
//     }
//   }
// `;
/////////////////////////////////////////////

//   <PlasticList>
//   <div>
//     <div className="title">육아 명단</div>
//     <ul className="list-wrapper">
//       <li className="my-plastic">
//         <div className="plastic-img" />
//         <div
//           className="nickname txt-bold"
//           style={{ marginRight: "10px;" }}
//         >
//           아가봉다리
//         </div>
//       </li>
//       <li className="my-plastic">
//         <div className="plastic-img" />
//         <div
//           className="nickname txt-bold"
//           style={{ marginRight: "10px;" }}
//         >
//           도도봉다리
//         </div>
//       </li>
//       <li className="my-plastic">
//         <div className="plastic-img" />
//         <div
//           className="nickname txt-bold"
//           style={{ marginRight: "10px;" }}
//         >
//           아름봉다리
//         </div>
//       </li>
//       <li className="my-plastic">
//         <div className="plastic-img" />
//         <div
//           className="nickname txt-bold"
//           style={{ marginRight: "10px;" }}
//         >
//           지인봉다리
//         </div>
//       </li>
//       <li className="my-plastic">
//         <div className="plastic-img" />
//         <div
//           className="nickname txt-bold"
//           style={{ marginRight: "10px;" }}
//         >
//           순현봉다리
//         </div>
//       </li>
//     </ul>
//   </div>
// </PlasticList>
{
  /* /////////////////////////////////////////// */
}
