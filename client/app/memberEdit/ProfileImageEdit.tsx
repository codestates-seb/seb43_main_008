"use client";
// 나중에 useEffect로 유저 관련 정보 받아오는 걸로 바꾸기
import Image from "next/image";
import { useState } from "react";
import { AiFillCamera } from "react-icons/ai";
import styled from "styled-components";

export default function ProfileImageEdit() {
  const [image] = useState<string | null>(null);
  return (
    <ProfileImageEditContainer>
      <EditDescriptionContainer>
        <EditDescriptionH1>내 정보를 변경합니다.</EditDescriptionH1>
        <EditDescription>
          이름은 <b>공백없이</b> 12자 이하,
        </EditDescription>
        <EditDescription>기호는 -_.만 사용 가능합니다.</EditDescription>
      </EditDescriptionContainer>
      <ImageContainer>
        {image ? <Image src={image} alt="Profile Picture" /> : <DefaultImage />}
        <CameraIconContainer>
          <AiFillCamera />
        </CameraIconContainer>
        <ImageEditInput type="file" accept="image/*" />
      </ImageContainer>
    </ProfileImageEditContainer>
  );
}

const ProfileImageEditContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  height: 120px;
  padding: 10px;
`;

const EditDescriptionContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

const EditDescriptionH1 = styled.h1`
  font-size: 17px;
  font-weight: 700;
  margin-bottom: 7px;
`;

const EditDescription = styled.p`
  color: #757575;
  font-size: 11px;
`;

const ImageContainer = styled.div`
  position: relative;
  height: 60px;
  width: 60px;
`;

const CameraIconContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  bottom: 0;
  right: 0;
  height: 20px;
  width: 20px;
  border-radius: 50%;
  background-color: #ffffff;
`;

const ImageEditInput = styled.input`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  cursor: pointer;
`;

const DefaultImage = styled.div`
  height: 60px;
  width: 60px;
  border-radius: 50%;
  background-color: #676767;
`;
