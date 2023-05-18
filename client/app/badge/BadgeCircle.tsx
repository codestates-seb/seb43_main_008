// "use client";

// import Image from "next/image";
// import styled from "styled-components";

// interface BadgeCircleProps {
//   image: { src: string; alt: string } | null;
//   isAcquired: boolean;
// }

// const BadgeCircle: React.FC<BadgeCircleProps> = ({ image, isAcquired }) => {
//   return (
//     <>
//       <ContentsContainer>
//         <BadgeImgContents
//           style={{
//             backgroundColor: isAcquired ? "#fff8de" : "#b5b5ba",
//           }}
//         >
//           {image && (
//             <Image
//               src={image.src}
//               alt={image.alt}
//               width={170}
//               height={170}
//               style={{
//                 filter: isAcquired ? "none" : "grayscale(100%)",
//               }}
//             />
//           )}
//         </BadgeImgContents>
//       </ContentsContainer>
//     </>
//   );
// };

// // 콘텐츠 박스 디테일 레이아웃
// const ContentsContainer = styled.div``;

// // 중앙부 획득 뱃지 콘텐츠
// const BadgeImgContents = styled(ContentsContainer)`
//   border: 1px solid #9b9ba0;
//   width: 240px;
//   height: 240px;
//   border-radius: 50%;
//   background-color: #fff8de;
//   margin-top: 25px;
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   flex-direction: column;
//   padding: 20px;
// `;

// export default BadgeCircle;

// "use client";

// import Image from "next/image";
// import styled from "styled-components";

// interface BadgeCircleProps {
//   image: { src: string; alt: string } | null;
//   isAcquired: boolean;
// }

// const BadgeCircle: React.FC<BadgeCircleProps> = ({ image, isAcquired }) => {
//   return (
//     <>
//       <ContentsContainer>
//         <BadgeImgContents>
//           {image && (
//             <Image src={image.src} alt={image.alt} width={170} height={170} />
//           )}
//         </BadgeImgContents>
//       </ContentsContainer>
//     </>
//   );
// };

// // 콘텐츠 박스 디테일 레이아웃
// const ContentsContainer = styled.div``;

// // 중앙부 획득 뱃지 콘텐츠
// const BadgeImgContents = styled(ContentsContainer)`
//   border: 1px solid #9b9ba0;
//   width: 240px;
//   height: 240px;
//   border-radius: 50%;
//   background-color: #fff8de;
//   margin-top: 25px;
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   flex-direction: column;
//   padding: 20px;
//   background-color: ${({ isAcquired }) => (isAcquired ? "#fff" : "#333")};
// `;

// export default BadgeCircle;

// "use client";

// import Image from "next/image";
// import styled from "styled-components";

// interface BadgeCircleProps {
//   image: { src: string; alt: string } | null;
//   isAcquired: boolean;
// }

// const BadgeCircle: React.FC<BadgeCircleProps> = ({ image, isAcquired }) => {
//   console.log(image, "image");
//   return (
//     <ContentsContainer>
//       <BadgeImgContents acquired={image?.isAcquired}>
//         {image && (
//           <Image
//             src={image.src}
//             alt={image.alt}
//             width={170}
//             height={170}
//             style={{
//               filter: isAcquired ? "none" : "grayscale(100%)",
//             }}
//           />
//         )}
//       </BadgeImgContents>
//     </ContentsContainer>
//   );
// };

// // 콘텐츠 박스 디테일 레이아웃
// const ContentsContainer = styled.div``;

// // 중앙부 획득 뱃지 콘텐츠
// const BadgeImgContents = styled(ContentsContainer)`
//   border: 1px solid #9b9ba0;
//   width: 240px;
//   height: 240px;
//   border-radius: 50%;
//   background-color: ${({ acquired }) => (acquired ? "#fff8de" : "#d5d5d9")};
//   margin-top: 25px;
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   flex-direction: column;
//   padding: 20px;
// `;

// export default BadgeCircle;
