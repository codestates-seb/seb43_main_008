const badgeLists = [
  {
    src: "/badgeIcons/signUp.svg",
    alt: "가입 뱃지",
    mainText: "가입 축하 기념 뱃지 획득!",
    subText: "10%를 달성하여 아래 뱃지를 획득해보세요~",
    isAcquired: true,
  },
  {
    src: "/badgeIcons/dog.svg",
    alt: "강아지 뱃지",
    mainText: "재활용률 10%를 달성하셨네요!",
    subText: "20%를 달성하여 아래 뱃지를 획득해보세요~",
    isAcquired: false,
  },
  {
    src: "/badgeIcons/appreciation.svg",
    alt: "감사 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    // isAcquired: true,
  },
  {
    src: "/badgeIcons/halloween.svg",
    alt: "할로윈 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    // isAcquired: true,
  },
  {
    src: "/badgeIcons/beach.svg",
    alt: "해변 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    // isAcquired: true,
  },
  {
    src: "/badgeIcons/blooming.svg",
    alt: "만개 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    // isAcquired: false, // 기본값은 false
  },
  {
    src: "/badgeIcons/family.svg",
    alt: "가족 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    // isAcquired: false,
  },
  {
    src: "/badgeIcons/shopping.svg",
    alt: "쇼핑 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    // isAcquired: false,
  },
  {
    src: "/badgeIcons/wedding.svg",
    alt: "결혼 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    // isAcquired: false,
  },
  {
    src: "/badgeIcons/cat.svg",
    alt: "고양이 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    // isAcquired: false,
  },
  {
    src: "/badgeIcons/waves.svg",
    alt: "레져 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    // isAcquired: false,
  },
  {
    src: "/badgeIcons/coffee.svg",
    alt: "커피 뱃지",
    mainText: "Image 1 Main Text",
    subText: "Image 1 Sub Text",
    // isAcquired: false,
  },
  // { src: "/badgeIcon/dog.svg", alt: "강아지뱃지" },
  // { src: "/badgeIcon/shopping.svg", alt: "쇼핑뱃지" },
  // { src: "/badgeIcon/dog.svg", alt: "강아지뱃지" },
  // { src: "/badgeIcon/shopping.svg", alt: "쇼핑뱃지" },
  // { src: "/badgeIcon/dog.svg", alt: "강아지뱃지" },
  // { src: "/badgeIcon/dog.svg", alt: "강아지뱃지" },
  // 필요한 만큼 추가
];

export default badgeLists;

// "use client";

// import Image from "next/image";
// import styled from "styled-components";

// interface BadgeCircleProps {
//   image: { src: string; alt: string } | null;
// }

// const BadgeCircle: React.FC<BadgeCircleProps> = ({ image }) => {
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
// `;

// export default BadgeCircle;

// "use client";
// import Image from "next/image";
// import { Dispatch, SetStateAction, useState } from "react";
// import styled from "styled-components";

// import badgeLists from "./BadgeLists";

// interface PlasticItemProps {
//   imageSrc: string;
//   altText: string;
//   onClick: () => void;
// }

// const PlasticItem: React.FC<PlasticItemProps> = ({
//   imageSrc,
//   altText,
//   onClick,
// }) => (
//   <li className="my-plastic" onClick={onClick}>
//     <div className="plastic-circle">
//       <Image src={imageSrc} alt={altText} width={35} height={35} />
//     </div>
//   </li>
// );

// interface BadgeDetailProps {
//   setSelectedImageDetail: Dispatch<
//     SetStateAction<{ src: string; alt: string } | null>
//   >;
// }

// interface ImageData {
//   src: string;
//   alt: string;
// }

// export default function BadgeDetail({
//   setSelectedImageDetail,
// }: BadgeDetailProps) {
//   const [, setSelectedImage] = useState<ImageData | null>(null);

//   const handleClick = (image: ImageData) => {
//     setSelectedImage(image);
//     setSelectedImageDetail(image);
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

// "use client";
// import { useState } from "react";
// import styled from "styled-components";

// import BadgeCircle from "./BadgeCircle";
// import BadgeDetail from "./BadgeDetail";

// export default function Badge() {
//   const [selectedImageDetail, setSelectedImageDetail] = useState<{
//     src: string;
//     alt: string;
//     // } | null>(null);
//   } | null>({
//     src: "/logoSquare.png", // 초기 이미지의 경로가져오기
//     alt: "쓰또 로고",
//   });

//   return (
//     <>
//       <MainContainer>
//         <MainText>재활용률 100%를 달성하셨네요!</MainText>
//         <SubText>300%를 달성하여 아래 뱃지를 획득해보세요!</SubText>
//         <BadgeCircle image={selectedImageDetail} />
//       </MainContainer>
//       <BadgeDetail setSelectedImageDetail={setSelectedImageDetail} />
//     </>
//   );
// }
// const MainContainer = styled.div`
//   display: flex;
//   justify-content: center;
//   align-items: center;
//   flex-direction: column;
//   height: 50vh; // 필요에 따라 위치 조정
// `;

// const MainText = styled.p`
//   font-size: 15px;
//   margin-top: 20px;
//   display: flex;
//   align-items: center;
//   justify-content: center;
// `;

// const SubText = styled.p`
//   font-size: 16px;
//   margin-top: 15px;
//   display: flex;
//   align-items: center;
//   justify-content: center;
// `;
