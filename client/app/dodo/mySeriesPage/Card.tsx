// // "use client";

// // import "react-responsive-carousel/lib/styles/carousel.min.css";

// // // import React, { useState } from "react";
// // // import React, { useEffect, useState } from "react";
// // import { Carousel } from "react-responsive-carousel";
// // import styled from "styled-components";

// // // 콘텐츠 박스 디테일 레이아웃
// // const ContentsContainer = styled.div`
// //   background-color: white;
// //   margin-top: 7px;
// //   display: flex;
// //   align-items: center;
// //   padding: 5px 11px 11px 11px;
// //   border: 1px, solid #757575;
// //   flex-direction: column;
// //   border-radius: 10px;
// // `;

// // const ImgContentBase = styled(ContentsContainer)`
// //   border: 1px solid #9b9ba0;
// //   padding: 5px;
// //   border-radius: 5px;
// //   width: 100%;
// //   height: 55vw;
// // `;

// // const SeriesContents = styled(ContentsContainer)`
// //   font-size: 16px;
// //   color: #222;
// //   line-height: 1.4; // 텍스트 라인 사이 간격 조절
// // `;

// // const BottomContainer = styled.div`
// //   display: flex;
// //   align-items: center;
// //   align-self: flex-start;
// // `;

// // const WriteTime = styled(BottomContainer)``;

// // // 캐러셀 스타일 적용
// // const CardCarousel = styled(Carousel)``;

// // export default function Card() {
// //   // const [items, setItems] = useState([]);

// //   // useEffect(() => {
// //   //   fetch("https://example.com/api/items") // 대상 API URL
// //   //     .then((response) => response.json())
// //   //     .then((data) => setItems(data));
// //   // }, []);

// //   return (
// //     <CardCarousel
// //       showArrows={false}
// //       infiniteLoop={true}
// //       showThumbs={false}
// //       autoPlay={false}
// //       interval={3000}
// //     >
// //       {/* {items.map((item) => (
// //         <ContentsContainer key={item.id}> */}
// //       <ContentsContainer>
// //         <ImgContentBase>
// //           {/* <ImgContents imageUrl="https://c4.wallpaperflare.com/wallpaper/108/140/869/digital-digital-art-artwork-fantasy-art-drawing-hd-wallpaper-thumb.jpg" /> */}
// //         </ImgContentBase>
// //         <SeriesContents>
// //           {/* {item.text} */}
// //           가만 보면 너는 진짜 너무해 때론 날 무심하게 내팽게 친 채 불안함이란
// //           벌을 내려줘 그럴 때면 난 길을 잃은 강아지가 돼 고개를 푹 숙인 채 걷네
// //           그냥 난 네 품에 안겨있고 싶은데 날이 선 너의 말들에 찔려서 피가 났어
// //           난 근데 걱정은커녕 혼자 낫는 법을 너는 알려줘 그러다가도 헷갈리게
// //           가끔은 예쁨을 내게 줘 그럴 때면 나는 어떻게 해야 될지 몰라 난 와르르
// //           무너지고 말아 나의 맘은 와르르 내게 이러지마 baby 너 없는 하루는
// //           상상하기 조차 싫은 걸 아무것도 할 수 없을 걸 난 바보가 돼버릴거야~
// //         </SeriesContents>
// //         <BottomContainer>
// //           <WriteTime
// //             style={{
// //               fontSize: "12px;",
// //               color: "#757575;",
// //               // opacity: "0.9;",
// //               marginLeft: "15px;",
// //             }}
// //           >
// //             2023년 5월 9일
// //             {/* {item.date} */}
// //           </WriteTime>
// //         </BottomContainer>
// //       </ContentsContainer>
// //       )}
// //     </CardCarousel>
// //   );
// // }

// // "use client";

// // import "react-responsive-carousel/lib/styles/carousel.min.css";

// // import React, { useState } from "react";
// // import { Carousel } from "react-responsive-carousel";
// // import styled from "styled-components";

// // import { Slide } from "./Slide"; // Slide component imported here

// // const ContentsContainer = styled.div`
// //   background-color: white;
// //   margin-top: 7px;
// //   display: flex;
// //   align-items: center;
// //   padding: 5px 11px 11px 11px;
// //   border: 1px, solid #757575;
// //   flex-direction: column;
// //   border-radius: 10px;
// // `;

// // const ImgContentBase = styled(ContentsContainer)`
// //   border: 1px solid #9b9ba0;
// //   padding: 5px;
// //   border-radius: 5px;
// //   width: 100%;
// //   height: 55vw;
// // `;

// // const SeriesContents = styled(ContentsContainer)`
// //   font-size: 16px;
// //   color: #222;
// //   line-height: 1.4;
// // `;

// // const BottomContainer = styled.div`
// //   display: flex;
// //   align-items: center;
// //   align-self: flex-start;
// // `;

// // const WriteTime = styled(BottomContainer)``;

// // // Slide data
// // const slides = [
// //   {
// //     id: 1,
// //     nickName: "Nick1",
// //     image: "https://example.com/image1.jpg",
// //     usageCount: 5,
// //   },
// //   {
// //     id: 2,
// //     nickName: "Nick2",
// //     image: "https://example.com/image2.jpg",
// //     usageCount: 7,
// //   },
// //   // More slides...
// // ];

// // export default function Card() {
// //   return (
// //     <ContentsContainer>
// //       <ImgContentBase>
// //         <Carousel>
// //           {slides.map((slide) => (
// //             <Slide key={slide.id} {...slide} />
// //           ))}
// //         </Carousel>
// //       </ImgContentBase>
// //       <SeriesContents>
// //         가만 보면 너는 진짜 너무해 때론 날 무심하게 내팽게 친 채 불안함이란 벌을
// //         내려줘 그럴 때면 난 길을 잃은 강아지가 돼 고개를 푹 숙인 채 걷네 그냥 난
// //         네 품에 안겨있고 싶은데 날이 선 너의 말들에 찔려서 피가 났어 난 근데
// //         걱정은커녕 혼자 낫는 법을 너는 알려줘 그러다가도 헷갈리게 가끔은 예쁨을
// //         내게 줘 그럴 때면 나는 어떻게 해야 될지 몰라 난 와르르 무너지고 말아
// //         나의 맘은 와르르 내게 이 이러지마 baby 너 없는 하루는 상상하기 조차 싫은
// //         걸 아무것도 할 수 없을 걸 난 바보가 돼버릴거야~
// //       </SeriesContents>
// //       <BottomContainer>
// //         <WriteTime
// //           style={{
// //             fontSize: "12px",
// //             color: "#757575",
// //             marginLeft: "15px",
// //           }}
// //         >
// //           2023년 5월 9일
// //         </WriteTime>
// //       </BottomContainer>
// //     </ContentsContainer>
// //   );
// // }

// // "use client";

// // import React from "react";
// // import styled from "styled-components";

// // // Slide data
// // const slides = [
// //   {
// //     id: 1,
// //     text: "text1",
// //     image:
// //       "https://fastly.picsum.photos/id/109/200/300.jpg?hmac=wtAwGwuVC3CUO3okhkSJZKm-wZY_evzXIo1F46OtKKo",
// //   },
// //   {
// //     id: 2,
// //     text: "text2",
// //     image:
// //       "https://fastly.picsum.photos/id/109/200/300.jpg?hmac=wtAwGwuVC3CUO3okhkSJZKm-wZY_evzXIo1F46OtKKo",
// //   },
// //   // More slides...
// // ];

// // const Slide = ({ image, text }) => (
// //   <SlideContainer>
// //     <img src={image} />
// //     <p>{text}</p>
// //   </SlideContainer>
// // );

// // const Slider = () => (
// //   <SliderContainer>
// //     {slides.map((slide) => (
// //       <Slide key={slide.id} {...slide} />
// //     ))}
// //   </SliderContainer>
// // );

// // const Card = () => (
// //   <CardContainer>
// //     <Slider />
// //     {/* Other components... */}
// //   </CardContainer>
// // );

// // const CardContainer = styled.div`
// //   /* style for CardContainer */
// // `;

// // const SliderContainer = styled.div`
// //   display: flex;
// //   overflow-x: scroll;

// //   /* Hide scrollbar */
// //   scrollbar-width: none; /* Firefox */
// //   -ms-overflow-style: none; /* IE and Edge */
// //   &::-webkit-scrollbar {
// //     display: none; /* Chrome, Safari and Opera */
// //   }
// // `;

// // const SlideContainer = styled.div`
// //   flex: 0 0 auto;
// //   width: 300px; /* adjust slide width as needed */
// //   margin-right: 10px; /* space between slides */
// //   /* style for SlideContainer */
// // `;

// // export default Card;

// // ("use client");
// // import "react-responsive-carousel/lib/styles/carousel.min.css"; // carousel 스타일

// // import { Carousel } from "react-responsive-carousel";
// // import styled from "styled-components";

// // 기존의 코드에 추가
// "use client";
// import styled from "styled-components";
// import { Carousel } from 'react-responsive-carousel';
// import "react-responsive-carousel/lib/styles/carousel.min.css"; // carousel 스타일

// // 기존의 코드에 추가
// const CardCarousel = styled(Carousel)`
//   // 캐러셀에 대한 추가적인 스타일을 여기에 적용하면 됩니다.
// `;

// export default function Card() {
//   return (
//     <CardCarousel
//       showArrows={true}
//       infiniteLoop={true}
//       showThumbs={false}
//       autoPlay={true}
//       interval={3000}
//     >
//       <div>
//         <ContentsContainer>
//           <ImgContentBase>
//             {/* <ImgContents imageUrl="https://c4.wallpaperflare.com/wallpaper/108/140/869/digital-digital-art-artwork-fantasy-art-drawing-hd-wallpaper-thumb.jpg" /> */}
//           </ImgContentBase>
//           <SeriesContents>
//             {/* 내용 */}
//           </SeriesContents>
//           <BottomContainer>
//             <WriteTime
//               style={{
//                 fontSize: "12px;",
//                 color: "#757575;",
//                 marginLeft: "15px;",
//               }}
//             >
//               2023년 5월 9일
//             </WriteTime>
//           </BottomContainer>
//         </ContentsContainer>
//       </div>
//       {/* 추가 카드 */}
//       <div>
//         {/* 카드 2 */}
//       </div>
//       <div>
//         {/* 카드 3 */}
//       </div>
//     </CardCarousel>
//   );
// }

// const CardCarousel = styled(Carousel)`
//   // 캐러셀에 대한 추가적인 스타일을 여기에 적용하면 됩니다.
// `;

// export default function Card() {
//   return (
//     <CardCarousel
//       showArrows={true}
//       infiniteLoop={true}
//       showThumbs={false}
//       autoPlay={true}
//       interval={3000}
//     >
//       <div>
//         <ContentsContainer>
//           <ImgContentBase>
//             {/* <ImgContents imageUrl="https://c4.wallpaperflare.com/wallpaper/108/140/869/digital-digital-art-artwork-fantasy-art-drawing-hd-wallpaper-thumb.jpg" /> */}
//           </ImgContentBase>
//           <SeriesContents>{/* 내용 */}</SeriesContents>
//           <BottomContainer>
//             <WriteTime
//               style={{
//                 fontSize: "12px;",
//                 color: "#757575;",
//                 marginLeft: "15px;",
//               }}
//             >
//               2023년 5월 9일
//             </WriteTime>
//           </BottomContainer>
//         </ContentsContainer>
//       </div>
//       {/* 추가 카드 */}
//       <div>{/* 카드 2 */}</div>
//       <div>{/* 카드 3 */}</div>
//     </CardCarousel>
//   );
// }

"use client";

import styled from "styled-components";

// 콘텐츠 박스 디테일 레이아웃
const ContentsContainer = styled.div`
  background-color: white;
  margin-top: 7px;
  display: flex;
  align-items: center;
  padding: 5px 11px 11px 11px;
  border: 1px, solid #757575;
  flex-direction: column;
  border-radius: 10px;
`;

const ImgContentBase = styled(ContentsContainer)`
  border: 1px solid #9b9ba0;
  padding: 5px;
  border-radius: 5px;
  width: 100%;
  height: 55vw;
`;

const SeriesContents = styled(ContentsContainer)`
  font-size: 16px;
  color: #222;
  line-height: 1.4; // 텍스트 라인 사이 간격 조절
`;

const BottomContainer = styled.div`
  display: flex;
  align-items: center;
  align-self: flex-start;
`;

const WriteTime = styled(BottomContainer)``;

export default function Card() {
  return (
    <ContentsContainer>
      <ImgContentBase>
        {/* <ImgContents imageUrl="https://c4.wallpaperflare.com/wallpaper/108/140/869/digital-digital-art-artwork-fantasy-art-drawing-hd-wallpaper-thumb.jpg" /> */}
      </ImgContentBase>
      <SeriesContents>
        가만 보면 너는 진짜 너무해 때론 날 무심하게 내팽게 친 채 불안함이란 벌을
        내려줘 그럴 때면 난 길을 잃은 강아지가 돼 고개를 푹 숙인 채 걷네 그냥 난
        네 품에 안겨있고 싶은데 날이 선 너의 말들에 찔려서 피가 났어 난 근데
        걱정은커녕 혼자 낫는 법을 너는 알려줘 그러다가도 헷갈리게 가끔은 예쁨을
        내게 줘 그럴 때면 나는 어떻게 해야 될지 몰라 난 와르르 무너지고 말아
        나의 맘은 와르르 내게 이러지마 baby 너 없는 하루는 상상하기 조차 싫은 걸
        아무것도 할 수 없을 걸 난 바보가 돼버릴거야~
      </SeriesContents>
      <BottomContainer>
        <WriteTime
          style={{
            fontSize: "12px;",
            color: "#757575;",
            // opacity: "0.9;",
            marginLeft: "15px;",
          }}
        >
          2023년 5월 9일
        </WriteTime>
      </BottomContainer>
    </ContentsContainer>
  );
}
