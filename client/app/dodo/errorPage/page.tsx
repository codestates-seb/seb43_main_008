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
        <Image src="/images/star.png" alt="별" width="190" height="300" />
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
