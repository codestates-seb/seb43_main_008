// "use client";
// import Image from "next/image";
// import { Dispatch, SetStateAction, useState } from "react";
// import styled from "styled-components";

// import badgeLists from "./BadgeData";

// interface PlasticItemProps {
//   imageSrc: string;
//   altText: string;
//   onClick: () => void;
//   isAcquired: boolean;
// }

// const PlasticItem: React.FC<PlasticItemProps> = ({
//   imageSrc,
//   altText,
//   onClick,
//   isAcquired,
// }) => (
//   <li className="my-plastic" onClick={onClick}>
//     <div
//       className="plastic-circle"
//       style={{
//         backgroundColor: isAcquired ? "#fff8de" : "#b5b5ba ",
//       }}
//     >
//       <Image
//         src={imageSrc}
//         alt={altText}
//         width={35}
//         height={35}
//         style={{
//           filter: isAcquired ? "none" : "grayscale(100%)",
//         }}
//       />
//     </div>
//   </li>
// );

// interface BadgeDetailProps {
//   setSelectedImageDetail: Dispatch<
//     SetStateAction<{ src: string; alt: string } | null>
//   >;
//   setMainText: Dispatch<SetStateAction<string>>;
//   setSubText: Dispatch<SetStateAction<string>>;

//   setIsAcquired: Dispatch<SetStateAction<boolean>>;
// }

// interface ImageData {
//   src: string;
//   alt: string;
//   mainText: string;
//   subText: string;
//   isAcquired: boolean;
// }

// export default function BadgeDetail({
//   setSelectedImageDetail,
//   setMainText,
//   setSubText,
//   setIsAcquired,
// }: // }: BadgeDetailProps) {
// //   const [, setSelectedImage] = useState<ImageData | null>(null);

// //   const handleClick = (image: ImageData) => {
// //     setSelectedImage(image);
// //     setSelectedImageDetail(image);

// //     // 뱃지에 따라 원하는 텍스트로 변경
// //     setMainText(image.mainText);
// //     setSubText(image.subText);

// //     // 뱃지가 획득되었는지 결정
// //     // 여기서는 badgeLists에 이미 획득한 뱃지의 정보를 추가해야 합니다.
// //     setIsAcquired(badgeLists.includes(image));
// //   };

// //   return (
// //     <>
// //       <CountCardContainer>
// //         <GetBadgeText>획득한 뱃지 List</GetBadgeText>
// //         <PlasticList>
// //           <ul className="list-wrapper">
// //             {badgeLists.map((image, index) => (
// //               <PlasticItem
// //                 key={index}
// //                 imageSrc={image.src}
// //                 altText={image.alt}
// //                 onClick={() => handleClick(image)}
// //                 isAcquired={image.isAcquired}
// //               />
// //             ))}
// //           </ul>
// //         </PlasticList>
// //       </CountCardContainer>
// //     </>
// //   );
// // }
// BadgeDetailProps): JSX.Element {
//   const [selectedPlastic, setSelectedPlastic] = useState<ImageData | null>(
//     badgeLists[0]
//   );

//   const handlePlasticClick = (plastic: ImageData) => {
//     setSelectedImageDetail({ src: plastic.src, alt: plastic.alt });
//     setMainText(plastic.mainText);
//     setSubText(plastic.subText);
//     setIsAcquired(plastic.isAcquired);
//   };

//   return (
//     <>
//       <CountCardContainer>
//         <GetBadgeText>획득한 뱃지 List</GetBadgeText>
//         <PlasticList>
//           <PlasticDetailWrapper>
//             <ul className="list-wrapper">
//               <li className="my-plastics">
//                 {badgeLists.map((plastic, index) => (
//                   <PlasticItem
//                     key={index}
//                     imageSrc={plastic.src}
//                     altText={plastic.alt}
//                     onClick={() => handlePlasticClick(plastic)}
//                     isAcquired={plastic.isAcquired}
//                   />
//                 ))}
//               </li>
//             </ul>
//           </PlasticDetailWrapper>
//         </PlasticList>
//       </CountCardContainer>
//     </>
//   );
// }

// const PlasticDetailWrapper = styled.div`
//   /* .my-plastics {
//     display: flex;
//     overflow: scroll;
//     margin-top: 15px;
//     margin-right: 10px;
//   }

//   .plastic-circle {
//     width: 60px;
//     height: 60px;
//     border-radius: 50%;
//     display: flex;
//     align-items: center;
//     justify-content: center;
//   } */
// `;

// const CountCardContainer = styled.div`
//   flex: 1;
//   color: #222;
//   background-color: white;
//   display: flex;
//   align-items: center;
//   flex-direction: column;
//   padding: 5px;
//   border-radius: 10px;
//   align-items: flex-start;
//   margin-top: 10px;
// `;
// const GetBadgeText = styled.div`
//   /* all: unset; */
//   font-size: 16px;
//   font-weight: bold;
//   margin-bottom: 5px;
//   margin-left: 7px;
//   margin-top: 10px;
// `;

// const PlasticList = styled.div`
//   border: 1px solid #9b9ba0;
//   padding: 5px;
//   border-radius: 5px;
//   background-color: white;
//   display: flex;
//   align-items: center;
//   justify-content: space-between;
//   width: 100%;
//   flex-direction: column;
//   align-items: flex-start;
//   margin-top: 5px;
//   flex-wrap: wrap;

//   .list-wrapper {
//     margin-top: 300px;
//     list-style: none;
//     margin: 5px;
//     display: flex;
//     gap: 7px;
//     flex-wrap: wrap;
//     justify-content: space-between;
//     max-width: calc(40px * 10 + 40px * 4);
//     background-color: red;
//   }

//   .my-plastic {
//     display: flex;
//     align-items: center;
//     margin: 5px 0;
//     justify-content: space-between;
//     flex-direction: column;

//     & > * + * {
//       margin-left: 10px;
//       flex: 1 1 calc(100% / 6); // 6열의 레이아웃으로 설정
//       margin: 5px 0;
//       max-width: calc(100% / 6); // 최대 너비도 6열 레이아웃에 맞게 설정
//     }
//   }

//   .plastic-circle {
//     width: 40px;
//     height: 40px;
//     border-radius: 50%;
//     background-color: #fff8de;
//     display: flex;
//     justify-content: center;
//     align-items: center;
//     text-align: center;
//   }
// `;

// "use client";
// import Image from "next/image";
// import { Dispatch, SetStateAction, useState } from "react";
// import styled from "styled-components";

// import badgeLists from "./BadgeData";

// interface PlasticItemProps {
//   imageSrc: string;
//   altText: string;
//   onClick: () => void;
//   isAcquired: boolean;
// }

// const PlasticItem: React.FC<PlasticItemProps> = ({
//   imageSrc,
//   altText,
//   onClick,
//   isAcquired,
// }) => (
//   <li className="my-plastic" onClick={onClick}>
//     <div className="plastic-circle">
//       <ImageContainer isAcquired={isAcquired}>
//         <Image
//           src={imageSrc}
//           alt={altText}
//           width={35}
//           height={35}
//           style={{
//             filter: isAcquired ? "none" : "grayscale(100%)",
//           }}
//         />
//       </ImageContainer>
//     </div>
//   </li>
// );

// interface BadgeDetailProps {
//   setSelectedImageDetail: Dispatch<
//     SetStateAction<{ src: string; alt: string } | null>
//   >;
//   setMainText: Dispatch<SetStateAction<string>>;
//   setSubText: Dispatch<SetStateAction<string>>;
// }

// interface ImageData {
//   src: string;
//   alt: string;
//   mainText: string;
//   subText: string;
//   isAcquired: boolean;
// }

// export default function BadgeDetail({
//   setSelectedImageDetail,
//   setMainText,
//   setSubText,
// }: BadgeDetailProps) {
//   const [, setSelectedImage] = useState<ImageData | null>(null);

//   const handleClick = (image: ImageData) => {
//     setSelectedImage(image);
//     setSelectedImageDetail(image);

//     // 뱃지에 따라 원하는 텍스트로 변경
//     setMainText(image.mainText);
//     setSubText(image.subText);
//   };

//   return (
//     <>
//       <CountCardContainer>
//         <GetBadgeText>획득한 뱃지 List</GetBadgeText>
//         <PlasticList>
//           <ul className="list-wrapper">
//             {badgeLists.map((image, index) => (
//               <PlasticItem
//                 key={index}
//                 imageSrc={image.src}
//                 altText={image.alt}
//                 onClick={() => handleClick(image)}
//                 isAcquired={image.isAcquired}
//               />
//             ))}
//           </ul>
//         </PlasticList>
//       </CountCardContainer>
//     </>
//   );
// }

// const CountCardContainer = styled.div`
//   flex: 1;
//   color: #222;
//   background-color: white;
//   display: flex;
//   align-items: center;
//   flex-direction: column;
//   padding: 5px;
//   border-radius: 10px;
//   align-items: flex-start;
//   margin-top: 10px;
// `;
// const GetBadgeText = styled.div`
//   /* all: unset; */
//   font-size: 16px;
//   font-weight: bold;
//   margin-bottom: 5px;
//   margin-left: 7px;
//   margin-top: 10px;
// `;

// const PlasticList = styled.div`
//   border: 1px solid #9b9ba0;
//   padding: 5px;
//   border-radius: 5px;
//   background-color: white;
//   display: flex;
//   align-items: center;
//   justify-content: space-between;
//   width: 100%;
//   flex-direction: column;
//   align-items: flex-start;
//   margin-top: 5px;

//   .list-wrapper {
//     margin-top: 300px;
//     list-style: none;
//     margin: 5px;
//     display: flex;
//     gap: 7px;
//     flex-wrap: wrap;
//     justify-content: space-between;
//     max-width: calc(40px * 10 + 40px * 4);
//   }

//   .my-plastic {
//     display: flex;
//     align-items: center;
//     margin: 5px 0;
//     justify-content: space-between;
//     flex-direction: column;

//     & > * + * {
//       margin-left: 10px;
//     }
//   }

//   .plastic-circle {
//     width: 40px;
//     height: 40px;
//     border-radius: 50%;
//     background-color: #fff8de;
//     display: flex;
//     justify-content: center;
//     align-items: center;
//     text-align: center;
//   }
// `;
// const ImageContainer = styled.div`
//   background-color: ${({ isAcquired }) => (isAcquired ? "#fff" : "#333")};
// `;

// "use client";
// import Image from "next/image";
// import { Dispatch, SetStateAction, useState } from "react";
// import styled from "styled-components";

// import badgeLists from "./BadgeData";

// interface PlasticItemProps {
//   imageSrc: string;
//   altText: string;
//   onClick: () => void;
//   isAcquired: boolean;
// }

// const PlasticItem: React.FC<PlasticItemProps> = ({
//   imageSrc,
//   altText,
//   onClick,
//   isAcquired,
// }) => (
//   <li className="my-plastic" onClick={onClick}>
//     <ImageContainer isAcquired={isAcquired}>
//       <Image
//         src={imageSrc}
//         alt={altText}
//         width={35}
//         height={35}
//         style={{
//           filter: isAcquired ? "none" : "grayscale(100%)",
//         }}
//       />
//     </ImageContainer>
//   </li>
// );

// interface BadgeDetailProps {
//   setSelectedImageDetail: Dispatch<
//     SetStateAction<{ src: string; alt: string } | null>
//   >;
//   setMainText: Dispatch<SetStateAction<string>>;
//   setSubText: Dispatch<SetStateAction<string>>;
//   setIsAcquired: Dispatch<SetStateAction<boolean>>;
// }

// interface ImageData {
//   src: string;
//   alt: string;
//   mainText: string;
//   subText: string;
//   isAcquired: boolean;
// }

// export default function BadgeDetail({
//   setSelectedImageDetail,
//   setMainText,
//   setSubText,
//   setIsAcquired,
//   setSelectedImage,
// }: BadgeDetailProps) {
//   const handleClick = (image: ImageData) => {
//     setSelectedImage(image);
//     setSelectedImageDetail(image);

//     // 뱃지에 따라 원하는 텍스트로 변경
//     setMainText(image.mainText);
//     setSubText(image.subText);

//     // 뱃지가 획득되었는지 결정
//     // 여기서는 badgeLists에 이미 획득한 뱃지의 정보를 추가해야 합니다.
//     setIsAcquired(badgeLists.includes(image));
//   };

//   return (
//     <>
//       <CountCardContainer>
//         <GetBadgeText>획득한 뱃지 List</GetBadgeText>
//         <PlasticList>
//           <ul className="list-wrapper">
//             {badgeLists.map((image, index) => (
//               <PlasticItem
//                 key={index}
//                 imageSrc={image.src}
//                 altText={image.alt}
//                 onClick={() => handleClick(image)}
//                 isAcquired={image.isAcquired}
//               />
//             ))}
//           </ul>
//         </PlasticList>
//       </CountCardContainer>
//     </>
//   );
// }

// const CountCardContainer = styled.div`
//   flex: 1;
//   color: #222;
//   background-color: white;
//   display: flex;
//   align-items: center;
//   flex-direction: column;
//   padding: 5px;
//   border-radius: 10px;
//   align-items: flex-start;
//   margin-top: 10px;
// `;
// const GetBadgeText = styled.div`
//   /* all: unset; */
//   font-size: 16px;
//   font-weight: bold;
//   margin-bottom: 5px;
//   margin-left: 7px;
//   margin-top: 10px;
// `;

// const PlasticList = styled.div`
//   border: 1px solid #9b9ba0;
//   padding: 5px;
//   border-radius: 5px;
//   background-color: white;
//   display: flex;
//   align-items: center;
//   justify-content: space-between;
//   width: 100%;
//   flex-direction: column;
//   align-items: flex-start;
//   margin-top: 5px;

//   .list-wrapper {
//     margin-top: 300px;
//     list-style: none;
//     margin: 5px;
//     display: flex;
//     gap: 7px;
//     flex-wrap: wrap;
//     justify-content: space-between;
//     max-width: calc(40px * 10 + 40px * 4);
//   }

//   .my-plastic {
//     display: flex;
//     align-items: center;
//     margin: 5px 0;
//     justify-content: space-between;
//     flex-direction: column;

//     & > * + * {
//       margin-left: 10px;
//     }
//   }
// `;

// const ImageContainer = styled.div`
//   width: 40px;
//   height: 40px;
//   border-radius: 50%;
//   background-color: ${({ isAcquired }) => (isAcquired ? "#fff8de" : "#d5d5d9")};
//   display: flex;
//   justify-content: center;
//   align-items: center;
//   text-align: center;
// `;
