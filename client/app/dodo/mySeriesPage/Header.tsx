// "use client";

// import Image from "next/image";
// import Link from "next/link";
// import React, { useState } from "react";
// // import { AiOutlineLeft } from "react-icons/ai";
// import styled from "styled-components";

// export default function Header({
//   backButton,
//   textContent,
//   mainButton,
// }: {
//   backButton: boolean;
//   textContent: string | null;
//   mainButton: boolean;
// }) {
//   // 홈버튼+백버튼 호버설정
//   const [isHovered, setIsHovered] = useState(false);
//   const [backIsHovered, setBackIsHovered] = useState(false);

//   return (
//     <HeaderContainer>
//       {backButton ? (
//         <BackArrowContainer
//           onMouseEnter={() => setBackIsHovered(true)}
//           onMouseLeave={() => setBackIsHovered(false)}
//         >
//           {/* <AiOutlineLeft size="24" /> */}
//           <Image
//             src={backIsHovered ? "/backBlack.svg" : "/backGrey.svg"}
//             alt="홈 버튼"
//             width="36"
//             height="36"
//           />
//         </BackArrowContainer>
//       ) : (
//         <BackArrowContainer />
//       )}
//       {textContent ? <HeaderText>{textContent}</HeaderText> : null}
//       {mainButton ? (
//         <MainButtonContainer>
//           <Link href="/">
//             <MainButton
//               onMouseEnter={() => setIsHovered(true)}
//               onMouseLeave={() => setIsHovered(false)}
//             >
//               <Image
//                 src={isHovered ? "/icons/HomeFill.svg" : "/homeGrey.svg"}
//                 alt="홈 버튼"
//                 width="26"
//                 height="26"
//               />
//             </MainButton>
//           </Link>
//         </MainButtonContainer>
//       ) : (
//         <MainButtonContainer />
//       )}
//     </HeaderContainer>
//   );
// }

// const HeaderContainer = styled.header`
//   position: sticky;
//   top: 0;
//   display: flex;
//   justify-content: space-between;
//   height: 44px;
//   background-color: #ffffff;
//   z-index: 1;
// `;

// const BackArrowContainer = styled.div`
//   display: flex;
//   align-items: center;
//   height: 50px;
//   width: 44px;
//   padding: 10px;
// `;

// const HeaderText = styled.span`
//   display: flex;
//   justify-content: center;
//   align-items: center;
//   height: 100%;
//   font-size: 16px;
// `;

// const MainButtonContainer = styled.div`
//   display: flex;
//   align-items: center;
//   height: 50px;
//   width: 44px;
//   padding: 10px 10px 10px 0;
// `;

// const MainButton = styled.button`
//   /* height: 100%;
//   width: 100%;
//   font-size: 13px;
//   font-weight: 700; */
//   background: inherit;
//   border: none;
//   border-radius: 0;
//   padding: 0;
// `;

"use client";

import Image from "next/image";
import Link from "next/link";
import { useState } from "react";
import { AiOutlineLeft } from "react-icons/ai";
import styled from "styled-components";

export default function Header({
  backButton,
  textContent,
  mainButton,
}: {
  backButton: boolean;
  textContent: string | null;
  mainButton: boolean;
}) {
  // 홈버튼 호버설정
  const [isHovered, setIsHovered] = useState(false);
  const mouseHandler = () => {
    setIsHovered(!isHovered);
  };

  return (
    <HeaderContainer>
      {backButton ? (
        <BackArrowContainer onMouseEnter={mouseHandler}>
          <div className={`hoverArrow ${isHovered ? "arrowOpacity" : null}`} />
          <AiOutlineLeft size="18" className={`${isHovered && "fillColor"}`} />
        </BackArrowContainer>
      ) : (
        <BackArrowContainer />
      )}
      {textContent ? <HeaderText>{textContent}</HeaderText> : null}
      {mainButton ? (
        <MainButtonContainer>
          <Link href="/">
            <MainButton
            // onMouseEnter={() => setIsHovered(true)}
            // onMouseLeave={() => setIsHovered(false)}
            >
              <Image
                // src={isHovered ? "/icons/HomeFill.svg" : "/homeGrey.svg"}
                src="/homeGrey.svg"
                alt="홈 버튼"
                width="18"
                height="18"
              />
            </MainButton>
          </Link>
        </MainButtonContainer>
      ) : (
        <MainButtonContainer />
      )}
    </HeaderContainer>
  );
}

const HeaderContainer = styled.header`
  position: sticky;
  top: 0;
  display: flex;
  justify-content: space-between;
  height: 44px;
  background-color: #ffffff;
  z-index: 1;
`;

const BackArrowContainer = styled.div`
  display: flex;
  align-items: center;
  height: 50px;
  width: 44px;
  padding: 10px;
  position: relative;
  cursor: pointer; // 커서를 손가락 모양으로 변경

  /* // 커서 주위 동그란 원; 호버
  > .hoverArrow {
    position: absolute;
    z-index: -1;
    left: 50%;
    top: 50%;
    width: 40px;
    height: 40px;
    background-color: red;
    transform: translate(-50%, -50%);
    border-radius: 50%;
    opacity: 0;
  }

  > .arrowOpacity {
    opacity: 1 !important;
  }

  > .fillColor {
    fill: white;
  } */
`;

const HeaderText = styled.span`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  font-size: 13px;
  margin-top: 3px;
`;

const MainButtonContainer = styled.div`
  display: flex;
  align-items: center;
  height: 50px;
  width: 44px;
  padding: 10px 10px 10px 0;
  margin-top: 3px;
`;

const MainButton = styled.button`
  height: 100%;
  width: 100%;
  font-weight: 700;
  background: inherit;
  border: none;
  border-radius: 0;
  padding: 0;
`;
