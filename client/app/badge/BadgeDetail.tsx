"use client";
import Image from "next/image";
import { Dispatch, SetStateAction, useState } from "react";
import styled from "styled-components";

import badgeLists from "./BadgeLists";

interface PlasticItemProps {
  imageSrc: string;
  altText: string;
  onClick: () => void;
}

const PlasticItem: React.FC<PlasticItemProps> = ({
  imageSrc,
  altText,
  onClick,
}) => (
  <li className="my-plastic" onClick={onClick}>
    <div className="plastic-circle">
      <Image src={imageSrc} alt={altText} width={40} height={40} />
    </div>
  </li>
);

interface BadgeDetailProps {
  setSelectedImageDetail: Dispatch<
    SetStateAction<{ src: string; alt: string } | null>
  >;
}

interface ImageData {
  src: string;
  alt: string;
}

export default function BadgeDetail({
  setSelectedImageDetail,
}: BadgeDetailProps) {
  const [, setSelectedImage] = useState<ImageData | null>(null);

  const handleClick = (image: ImageData) => {
    setSelectedImage(image);
    setSelectedImageDetail(image);
  };

  return (
    <>
      <CountCardContainer>
        <GetBadgeText>획득한 뱃지 List</GetBadgeText>
        <PlasticList>
          <ul className="list-wrapper">
            {badgeLists.map((image, index) => (
              <PlasticItem
                key={index}
                imageSrc={image.src}
                altText={image.alt}
                onClick={() => handleClick(image)}
              />
            ))}
          </ul>
        </PlasticList>
      </CountCardContainer>
    </>
  );
}

const CountCardContainer = styled.div`
  flex: 1;
  color: #222;
  background-color: white;
  display: flex;
  align-items: center;
  flex-direction: column;
  padding: 5px;
  border-radius: 10px;
  align-items: flex-start;
`;
const GetBadgeText = styled.div`
  /* all: unset; */
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 5px;
  margin-left: 7px;
  margin-top: 10px;
`;

const PlasticList = styled.div`
  border: 1px solid #9b9ba0;
  padding: 5px;
  border-radius: 5px;
  background-color: white;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  flex-direction: column;
  align-items: flex-start;
  margin-top: 5px;

  .list-wrapper {
    margin-top: 300px;
    list-style: none;
    margin: 5px;
    display: flex;
    gap: 7px;
    flex-wrap: wrap;
    justify-content: space-between;
    max-width: calc(40px * 10 + 40px * 4);
  }

  .my-plastic {
    display: flex;
    align-items: center;
    margin: 5px 0;
    justify-content: space-between;
    flex-direction: column;

    & > * + * {
      margin-left: 10px;
    }
  }

  .plastic-circle {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    background-color: #fff8de;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
  }
`;
