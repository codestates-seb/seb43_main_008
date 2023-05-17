// "use client";
// import React from "react";
// import styled from "styled-components";
// import InfiniteScroll from "react-infinite-scroll-component";

// const CountCardContainer = styled.div`
//   flex: 1;
//   color: #222;
//   background-color: white;
//   display: flex;
//   align-items: center;
//   flex-direction: column;
//   padding: 5px 11px 11px 11px;
//   border-radius: 10px;
//   align-items: flex-start;
// `;

// const CounterText = styled.div`
//   /* all: unset; */
//   font-size: 16px;
//   font-weight: bold;
//   margin-bottom: 5px;
//   margin-left: 7px;
// `;

// const PlasticListContainer = styled.div`
//   border: 1px solid #9b9ba0;
//   padding: 5px;
//   border-radius: 5px;
//   background-color: white;
//   display: flex;
//   align-items: center;
//   justify-content: space-between;
//   position: sticky;
//   top: 74px;
//   width: 100%;

//   .list-wrapper {
//     margin-top: 35px;
//     list-style: none;
//     margin: 0 auto;
//     padding: 10px;
//     display: flex;
//   }

//   .my-plastic {
//     display: flex;
//     align-items: center;
//     margin-bottom: 3px;
//   }

//   .plastic-circle {
//     width: 30px;
//     height: 30px;
//     border-radius: 50%;
//     background-color: #fff8de;
//     margin-right: 12px;

//     display: flex;
//     justify-content: center;
//     align-items: center;
//     text-align: center;
//   }
// `;

// export default function CountCard() {
//   return (
//     <CountCardContainer>
//       <CounterText>사용 횟수</CounterText>
//       <PlasticListContainer>
//         <ul className="list-wrapper">
//           <li className="my-plastic">
//             <div className="plastic-circle">1회</div>
//           </li>

//           <li className="my-plastic">
//             <div className="plastic-circle">2회</div>
//           </li>

//           <li className="my-plastic">
//             <div className="plastic-circle">3회</div>
//           </li>

//           <li className="my-plastic">
//             <div className="plastic-circle">4회</div>
//           </li>

//           <li className="my-plastic">
//             <div className="plastic-circle">5회</div>
//           </li>

//           <li className="my-plastic">
//             <div className="plastic-circle">6회</div>
//           </li>
//         </ul>
//       </PlasticListContainer>
//     </CountCardContainer>
//   );
// }

"use client";
// import React, { useState } from "react";
// import InfiniteScroll from "react-infinite-scroll-component";
import styled from "styled-components";

const CountCardContainer = styled.div`
  flex: 1;
  color: #222;
  background-color: white;
  display: flex;
  align-items: center;
  flex-direction: column;
  padding: 5px 11px 11px 11px;
  border-radius: 10px;
  align-items: flex-start;
`;

const CounterText = styled.div`
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 5px;
  margin-left: 7px;
`;

const PlasticListContainer = styled.div`
  border: 1px solid #9b9ba0;
  padding: 5px;
  border-radius: 5px;
  background-color: white;
  display: flex;
  align-items: center;
  justify-content: space-between;
  position: sticky;
  top: 74px;
  width: 100%;

  .list-wrapper {
    margin-top: 35px;
    list-style: none;
    margin: 0 auto;
    padding: 10px;
    display: flex;
    overflow-x: auto;
  }
`;

// const PlasticCircle = styled.div`
//   width: 30px;
//   height: 30px;
//   border-radius: 50%;
//   background-color: #fff8de;
//   margin-right: 12px;

//   display: flex;
//   justify-content: center;
//   align-items: center;
//   text-align: center;
// `;

export default function CountCard() {
  // const [items, setItems] = useState(
  //   Array.from({ length: 7 }, (_, i) => i + 1)
  // );

  // const fetchMoreData = () => {
  //   setTimeout(() => {
  //     setItems(
  //       items.concat(Array.from({ length: 6 }, (_, i) => items.length + i + 1))
  //     );
  //   }, 1500);
  // };

  return (
    <CountCardContainer>
      <CounterText>사용 횟수</CounterText>
      <PlasticListContainer>
        {/* <InfiniteScroll
          dataLength={items.length}
          next={fetchMoreData}
          style={{ display: "flex", flexDirection: "row", overflowX: "auto" }}
          hasMore={true}
          scrollThreshold="200px"
          inverse={false}
          horizontal
        >
          {items.map((_, index) => (
            <div key={index} className="my-plastic">
              <PlasticCircle>{index + 1}회</PlasticCircle>
            </div>
          ))}
        </InfiniteScroll> */}
      </PlasticListContainer>
    </CountCardContainer>
  );
}
