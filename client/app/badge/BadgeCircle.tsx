"use client";

import Image from "next/image";
import styled from "styled-components";

interface BadgeCircleProps {
  image: { src: string; alt: string } | null;
}

const BadgeCircle: React.FC<BadgeCircleProps> = ({ image }) => {
  return (
    <>
      <ContentsContainer>
        <BadgeImgContents>
          {image && (
            <Image src={image.src} alt={image.alt} width={200} height={200} />
          )}
        </BadgeImgContents>
      </ContentsContainer>
    </>
  );
};

// 콘텐츠 박스 디테일 레이아웃
const ContentsContainer = styled.div``;

// 중앙부 획득 뱃지 콘텐츠
const BadgeImgContents = styled(ContentsContainer)`
  border: 1px solid #9b9ba0;
  width: 200px;
  height: 200px;
  border-radius: 50%;
  background-color: #fff8de;
  margin-top: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 20px;
`;

export default BadgeCircle;
