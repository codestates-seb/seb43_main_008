"use client";

import Image from "next/image";
import styled from "styled-components";

interface BadgeCircleProps {
  image: { src: string; alt: string } | null;
  isAcquired: boolean;
  clickedImage: any;
}

const BadgeCircle: React.FC<BadgeCircleProps> = ({
  image,
  isAcquired,
  clickedImage,
}) => {
  return (
    <>
      <BadgeImgContents
        style={{
          backgroundColor: isAcquired ? "#eff4e7;" : "#eff4e7",
        }}
      >
        {image &&
          (clickedImage === null ? (
            <Image
              src={image.src}
              alt={image.alt}
              width={170}
              height={170}
              style={{
                filter: isAcquired ? "none" : "grayscale(100%)",
              }}
            />
          ) : (
            <Image
              src={clickedImage}
              alt={image.alt}
              width={180}
              height={180}
              style={{
                filter: isAcquired ? "none" : "grayscale(100%)",
                borderRadius: "50%",
              }}
            />
          ))}
      </BadgeImgContents>
    </>
  );
};

// 중앙부 획득 뱃지 콘텐츠
const BadgeImgContents = styled.div`
  border: 1px solid #3f910c;
  width: 200px;
  height: 200px;
  border-radius: 50%;
  background-color: #eff4e7;
  margin-top: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 18px;
`;

export default BadgeCircle;
