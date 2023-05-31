"use client";
import Image from "next/image";
import { Dispatch, SetStateAction, useState } from "react";
import styled from "styled-components";

import axiosInstance from "../axiosInstance";
// import badgeLists from "./BadgeData"; // 목데이터임 백에서 받아오게 되면 주석 처리 해야지 !

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
      // style={{
      //   backgroundColor: isAcquired ? "#eff4e7" : "#b2b2b2",
      // }}
    >
      <Image
        src={imageSrc}
        alt={altText}
        width={35}
        height={60}
        style={{
          filter: isAcquired ? "none" : "grayscale(100%)",
          borderRadius: "50%",
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
  setClickedImage: any;
  setMyBadge: any;
}

interface ImageData {
  src: string;
  alt: string;
  mainText: string;
  subText: string;
  isAcquired: boolean;
  img?: string;
  badgeId?: string;
}

export default function BadgeDetail({
  setSelectedImageDetail,
  setMainText,
  setSubText,
  setIsAcquired,
  badgeList,
  setClickedImage,
  setMyBadge,
}: BadgeDetailProps) {
  const [, setSelectedImage] = useState<ImageData | null>(null);

  const handleClick = (image: ImageData) => {
    setSelectedImage(image);
    setSelectedImageDetail(image);

    // 뱃지에 따라 원하는 텍스트로 변경
    setMainText(image.mainText);
    setSubText(image.subText);
    setIsAcquired(image.isAcquired);

    // 뱃지가 획득되었는지 결정
    // 여기서는 badgeLists에 이미 획득한 뱃지의 정보를 추가
    // setIsAcquired(badgeLists.includes(image));

    setClickedImage(image.img);
    // console.log(image);
  };

  const getInfo = async (image: ImageData) => {
    setClickedImage(image.img);
    // console.log(image);
    try {
      const response = await axiosInstance.get(
        `/members/badge/${image.badgeId}`
      );
      // console.log(response.data.data);
      setMyBadge(response.data.data);
    } catch (error) {
      // console.error("Error fetching badge list:", error);
    }
  };

  return (
    <>
      <BadgeDetailContainer>
        <GetBadgeText>획득한 뱃지 List</GetBadgeText>
        <CountCardContainer>
          <PlasticList>
            <ul className="list-wrapper">
              {badgeList.badgeList.map(
                (
                  image: any //////////////오오오오오오오오
                ) => (
                  // {badgeLists.map((image: any) => (
                  <PlasticItem
                    key={image.badgeId}
                    imageSrc={image.img}
                    altText={"badge"}
                    onClick={() => {
                      handleClick(image);
                      getInfo(image);
                    }}
                    isAcquired={image.isAcquired}
                  />
                )
              )}
            </ul>
          </PlasticList>
        </CountCardContainer>
      </BadgeDetailContainer>
    </>
  );
}

const CountCardContainer = styled.div`
  flex: 1;
  background-color: white;
  align-items: center;
  flex-direction: column;
  padding: 7px;
  border-radius: 10px;
  align-items: flex-start;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
  width: 280px;
`;
const GetBadgeText = styled.div`
  /* all: unset; */
  font-size: 14px;
  margin-right: 170px;
  margin-bottom: 2px;
  /* align-self: flex-start; */
  color: #222;
`;

const PlasticList = styled.div`
  width: 100%;
  height: 175px;
  /* overflow-y: scroll; */
  display: flex;
  justify-content: center;
  display: flex;
  align-items: center;
  flex-direction: column;

  .list-wrapper {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    grid-gap: 11px;
    width: 95%;
    padding: 1px;
    list-style: none;
    background-color: 3;
    /* background-color: red; */

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
      background-color: #eff4e7;
      /* border: #3f910c 1px solid; */
      object-fit: "cover";
    }
  }
`;

const BadgeDetailContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`;
