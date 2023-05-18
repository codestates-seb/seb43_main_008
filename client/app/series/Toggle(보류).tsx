// < 토글로 헤더 모달 구현>
// "use client";
// import { useState } from "react";
// import { AiOutlineLeft } from "react-icons/ai";
// import { VscGistSecret } from "react-icons/vsc";
// // import { GiPadlock } from "react-icons/gi";
// import styled from "styled-components";

// const Modal = styled.div`
//   position: fixed;
//   z-index: 1;
//   right: 15px;
//   top: 40px;
//   overflow: none;
// `;

// const ModalContent = styled.div`
//   background-color: #fefefe;
//   padding: 10px;
//   border: 1px solid #9b9ba0;
//   width: 230px;
//   border-radius: 10px;
// `;

// const Close = styled.span`
//   color: #757575;
//   float: right;
//   font-size: 14px;
//   font-weight: bold;

//   &:hover,
//   &:focus {
//     color: #222;
//     text-decoration: none;
//     cursor: pointer;
//   }
// `;

// const CheckboxLabel = styled.label`
//   position: relative;
//   display: inline-block;
//   width: 40px;
//   height: 20px;
//   /* background-color: red; */
// `;

// const CheckboxSlider = styled.span`
//   position: absolute;
//   cursor: pointer;
//   border-radius: 3px;
//   top: 0;
//   left: 0;
//   right: 0;
//   bottom: 0;
//   background-color: #ccc;
//   transition: 0.4s;

//   &:before {
//     position: absolute;
//     content: "";
//     height: 18px;
//     width: 19px;
//     left: 1px;
//     bottom: 1px;
//     background-color: white;
//     border-radius: 3px;
//     transition: 0.4s;
//   }

//   &:after {
//     content: "비공개";
//     color: black;
//     display: block;
//     position: absolute;
//     transform: translate(-50%, -50%);
//     top: 50%;
//     left: 50%;
//     font-size: 5px;
//   }
// `;

// const HeaderContainer = styled.header`
//   position: sticky;
//   top: 0;
//   display: flex;
//   justify-content: space-between;
//   height: 44px;
//   background-color: #ffffff;
//   z-index: 1;
// `;

// const CheckboxInput = styled.input`
//   opacity: 0;
//   width: 0;
//   height: 0;

//   &:checked + ${CheckboxSlider} {
//     background-color: #dfdfe1;

//     &:before {
//       transform: translateX(19px);
//     }

//     &:after {
//       content: "공개";
//     }
//   }

//   &:focus + ${CheckboxSlider} {
//     box-shadow: 0 0 1px #9b9ba0;
//   }
// `;

// const BackArrowContainer = styled.div`
//   display: flex;
//   align-items: center;
//   height: 50px;
//   width: 44px;
//   padding: 10px;
//   position: relative;
//   cursor: pointer; // 커서를 손가락 모양으로 변경
// `;

// const HeaderText = styled.span`
//   display: flex;
//   justify-content: center;
//   align-items: center;
//   height: 100%;
//   font-size: 13px;
//   margin-top: 3px;
// `;

// const SecretButtonContainer = styled.div`
//   display: flex;
//   align-items: center;
//   height: 50px;
//   width: 50px;
//   padding: 10px 10px 10px 0;
//   margin-top: 3px;
// `;

// const SecretButton = styled.button`
//   height: 100%;
//   width: 100%;
//   background: inherit;
//   border: none;
//   border-radius: 0;
//   padding: 0;
// `;

// const SecretButtonContent = styled.div`
//   display: flex;
//   flex-direction: column;
//   align-items: center;
//   justify-content: center;
//   height: 100%;
//   width: 100%;
// `;

// const SecretButtonText = styled.span`
//   font-size: 6px;
// `;

// export default function Header({
//   backButton,
//   textContent,
//   secretButton,
// }: {
//   backButton: boolean;
//   textContent: string | null;
//   secretButton: boolean;
// }) {
//   const [isModalOpen, setIsModalOpen] = useState(false);
//   const [isPublic, setIsPublic] = useState(false);

//   return (
//     <HeaderContainer>
//       {backButton ? (
//         <BackArrowContainer>
//           <AiOutlineLeft size="18" />
//         </BackArrowContainer>
//       ) : (
//         <BackArrowContainer />
//       )}
//       {textContent ? <HeaderText>{textContent}</HeaderText> : null}
//       {SecretButton ? (
//         <SecretButtonContainer onClick={() => setIsModalOpen(true)}>
//           <SecretButton>
//             <SecretButtonContent>
//               <VscGistSecret size="15" />
//               <SecretButtonText>공개 설정</SecretButtonText>
//             </SecretButtonContent>
//           </SecretButton>
//         </SecretButtonContainer>
//       ) : (
//         <SecretButtonContainer />
//       )}

//       {isModalOpen && (
//         <Modal>
//           <ModalContent>
//             <Close onClick={() => setIsModalOpen(false)}>&times;</Close>
//             <p
//               style={{
//                 marginBottom: "4px",
//                 fontSize: "13px",
//               }}
//             >
//               작성한 시리즈를 공개하시겠습니까?
//             </p>
//             <CheckboxLabel>
//               <CheckboxInput
//                 type="checkbox"
//                 checked={isPublic}
//                 onChange={() => setIsPublic(!isPublic)}
//               />
//               <CheckboxSlider />
//             </CheckboxLabel>
//           </ModalContent>
//         </Modal>
//       )}
//     </HeaderContainer>
//   );
// }

// <라디오 버튼으로 구현>
// "use client";
// import { useState } from "react";
// import { AiOutlineLeft } from "react-icons/ai";
// import { VscGistSecret } from "react-icons/vsc";
// // import HeaderModal from "./HeaderModal";
// import styled from "styled-components";

// const Modal = styled.div`
//   position: fixed;
//   z-index: 1;
//   right: 48px;
//   top: 70px;
//   overflow: none;
// `;

// const ModalContent = styled.div`
//   background-color: #fefefe;
//   padding: 10px;
//   border: 1px solid #9b9ba0;
//   width: 280px;
//   height: 225px;
//   border-radius: 10px;
// `;

// // 공개 여부 설정 모달 -> 라디오 버튼 설정
// const RadioGroup = styled.div`
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   gap: 25px;
// `;

// const RadioLabel = styled.label`
//   display: flex;
//   align-items: center;
//   cursor: pointer;
//   font-size: 12px;
//   line-height: 20px;
//   color: #757575;
//   margin-top: 10px;
// `;

// const RadioInput = styled.input`
//   margin-right: 8px;
// `;

// // 공개 비공개 여부 확인 메세지
// const ConfirmationMessage = styled.p`
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   margin-top: 15px;
//   font-size: 12px;
//   line-height: 1.4;
// `;

// const ConfirmButton = styled.button`
//   background-color: #fcfcfd;
//   color: #222;
//   border: 1px solid rgba(34, 36, 38, 0.5);
//   padding: 0.3rem 0.5rem;
//   margin-left: 110px;
//   border-radius: 3px;
//   margin-top: 15px;
//   cursor: pointer;
// `;

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
//   position: relative;
//   cursor: pointer; // 커서를 손가락 모양으로 변경
// `;

// const HeaderText = styled.span`
//   display: flex;
//   justify-content: center;
//   align-items: center;
//   height: 100%;
//   font-size: 13px;
//   margin-top: 3px;
// `;

// const SecretButtonContainer = styled.div`
//   display: flex;
//   align-items: center;
//   height: 50px;
//   width: 50px;
//   padding: 10px 10px 10px 0;
//   margin-top: 3px;
// `;

// const SecretButton = styled.button`
//   height: 100%;
//   width: 100%;
//   background: inherit;
//   border: none;
//   border-radius: 0;
//   padding: 0;
// `;

// const SecretButtonContent = styled.div`
//   display: flex;
//   flex-direction: column;
//   align-items: center;
//   justify-content: center;
//   height: 100%;
//   width: 100%;
// `;

// const SecretButtonText = styled.span`
//   font-size: 6px;
// `;

// export default function Header({
//   backButton,
//   textContent,
//   secretButton,
// }: {
//   backButton: boolean;
//   textContent: string | null;
//   secretButton: boolean;
// }) {
//   const [isModalOpen, setIsModalOpen] = useState(false);
//   const [isPublic, setIsPublic] = useState(false);

// const handleConfirm = () => {
//   // 여기에 라디오 버튼을 선택 -> 확인 버튼을 눌렀을 때, 이 설정을 어딘가에 저장하는 로직을 추가 가능

//   setIsModalOpen(false);
// };

//   return (
//     <HeaderContainer>
//       {backButton ? (
//         <BackArrowContainer>
//           <AiOutlineLeft size="18" />
//         </BackArrowContainer>
//       ) : (
//         <BackArrowContainer />
//       )}
//       {textContent ? <HeaderText>{textContent}</HeaderText> : null}
//       {SecretButton ? (
//         <SecretButtonContainer onClick={() => setIsModalOpen(true)}>
//           <SecretButton>
//             <SecretButtonContent>
//               <VscGistSecret size="15" />
//               <SecretButtonText>공개 설정</SecretButtonText>
//             </SecretButtonContent>
//           </SecretButton>
//         </SecretButtonContainer>
//       ) : (
//         <SecretButtonContainer />
//       )}

//       {isModalOpen && (
//         <Modal>
//           <ModalContent>
//             <p
//               style={{
//                 marginBottom: "4px",
//                 marginTop: "10px",
//                 marginLeft: "13px",
//                 fontSize: "13px",
//               }}
//             >
//               작성한 시리즈의 공개 여부를 선택해 주세요❤️
//             </p>
//             <RadioGroup>
//               <RadioLabel>
//                 <RadioInput
//                   type="radio"
//                   value="public"
//                   checked={isPublic}
//                   onChange={() => setIsPublic(true)}
//                 />
//                 공개
//               </RadioLabel>
//               <RadioLabel>
//                 <RadioInput
//                   type="radio"
//                   value="private"
//                   checked={!isPublic}
//                   onChange={() => setIsPublic(false)}
//                 />
//                 비공개
//               </RadioLabel>
//             </RadioGroup>
//             <ConfirmationMessage>
//               {isPublic ? "공개로 설정하였습니다!" : "비공개로 설정하였습니다!"}
//             </ConfirmationMessage>
//             <ConfirmButton onClick={handleConfirm}>확인</ConfirmButton>
//           </ModalContent>
//         </Modal>
//       )}
//     </HeaderContainer>
//   );
// }
