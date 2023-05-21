"use client";

import Image from "next/image";
import { useEffect, useState } from "react";
import { AiFillCamera } from "react-icons/ai";

// import { memberEdit } from "../api/memberEdit";
import { GetProfile } from "../api/myPageApi";
import * as S from "./ProfileEditStyle";

export default function ProfileEdit() {
  const [image, setImage] = useState<string | null>(null);
  const [nickName, setNickName] = useState<string>("");
  const [introduce, setIntroduce] = useState<string>("");

  useEffect(() => {
    GetProfile().then((res) => {
      setImage(res.image);
      setNickName(res.nickName);
      setIntroduce(res.introduce);
    });
  }, []);

  const handleImageUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setImage(reader.result as string);
      };
      reader.readAsDataURL(file);
    }
  };

  // const onSubmit = (nickName: string, introduce: string) => {
  //   memberEdit(nickName, introduce).then((res) => {
  //     console.log(res);
  //   });
  // };

  return (
    <>
      <S.ProfileImageEditContainer>
        <S.EditDescriptionContainer>
          <S.EditDescriptionH1>내 정보를 변경합니다.</S.EditDescriptionH1>
          <S.EditDescription>
            이름은 <b>공백없이</b> 12자 이하,
          </S.EditDescription>
          <S.EditDescription>기호는 -_.만 사용 가능합니다.</S.EditDescription>
        </S.EditDescriptionContainer>
        <S.ImageContainer>
          {image ? (
            <Image src={image} alt="Profile picture" layout="fill" />
          ) : (
            <S.DefaultImage />
          )}
          <S.CameraIconContainer>
            <AiFillCamera />
          </S.CameraIconContainer>
          <S.ImageEditInput
            type="file"
            accept="image/*"
            onChange={handleImageUpload}
          />
        </S.ImageContainer>
      </S.ProfileImageEditContainer>

      <S.ProfileEditContainer>
        <form>
          <S.NicknameInput
            type="text"
            value={nickName}
            onChange={(e) => setNickName(e.target.value)}
          />
          <S.MemberDescriptionTextarea
            value={introduce}
            onChange={(e) => setIntroduce(e.target.value)}
          />
          <S.SubmitButton type="button">수정하기</S.SubmitButton>
        </form>
      </S.ProfileEditContainer>
    </>
  );
}
