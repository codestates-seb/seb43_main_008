"use client";
import Image from "next/image";
import { Dispatch, SetStateAction, useState } from "react";
import styled from "styled-components";

// import badgeLists from "./BadgeData";

interface PlasticItemProps {
  imageSrc: string;
  altText: string;
  onClick: () => void;
  isAcquired: boolean;
}

const PlasticItem: React.FC<PlasticItemProps> = ({
  imageSrc,
  altText,
  onClick,
  isAcquired,
}) => (
  <li className="my-plastic" onClick={onClick}>
    <div
      className="plastic-circle"
      style={{
        backgroundColor: isAcquired ? "#fff8de" : "#757575",
      }}
    >
      <Image
        src={imageSrc}
        alt={altText}
        width={35}
        height={35}
        style={{
          filter: isAcquired ? "none" : "grayscale(100%)",
        }}
      />
    </div>
  </li>
);

interface BadgeDetailProps {
  setSelectedImageDetail: Dispatch<
    SetStateAction<{ src: string; alt: string } | null>
  >;
  setMainText: Dispatch<SetStateAction<string>>;
  setSubText: Dispatch<SetStateAction<string>>;
  setIsAcquired: Dispatch<SetStateAction<boolean>>;
  badgeList: any; // from the parent component
}

interface ImageData {
  src: string;
  alt: string;
  mainText: string;
  subText: string;
  isAcquired: boolean;
}

export default function BadgeDetail({
  setSelectedImageDetail,
  setMainText,
  setSubText,
  setIsAcquired,
  badgeList,
}: BadgeDetailProps) {
  const [, setSelectedImage] = useState<ImageData | null>(null);

  const handleClick = (image: ImageData) => {
    setSelectedImage(image);
    setSelectedImageDetail(image);

    // 뱃지에 따라 원하는 텍스트로 변경
    setMainText(image.mainText);
    setSubText(image.subText);

    // 뱃지가 획득되었는지 결정
    // 여기서는 badgeLists에 이미 획득한 뱃지의 정보를 추가
    // setIsAcquired(badgeLists.includes(image));
  };

  return (
    <>
      <CountCardContainer>
        <GetBadgeText>획득한 뱃지 List</GetBadgeText>
        <PlasticList>
          <ul className="list-wrapper">
            {badgeList.badgeList.map((image: any) => (
              <PlasticItem
                key={image.badgeId}
                imageSrc={image.img}
                altText={"badge"}
                onClick={() => handleClick(image)}
                isAcquired={image.isAcquired}
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
  /* border: #3f910c 1px solid; */
`;
const GetBadgeText = styled.div`
  /* all: unset; */
  font-size: 15px;
  font-weight: bold;
  margin-bottom: 5px;
  margin-left: 5px;
  align-self: flex-start;
  color: #222;
`;

const PlasticList = styled.div`
  width: 100%;
  height: 180px;
  overflow-y: scroll;
  display: flex;
  justify-content: center;

  .list-wrapper {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    grid-gap: 10px;
    width: 100%;
    padding: 0;
    list-style: none;
    background-color: 3;

    .my-plastic {
      cursor: pointer;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .plastic-circle {
      width: 50px;
      height: 50px;
      border-radius: 35px;
      display: flex;
      align-items: center;
      justify-content: center;
      background-color: #fff8de;
      /* border: #3f910c 1px solid; */
    }
  }
`;
