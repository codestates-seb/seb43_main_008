"use client";

import Image from "next/image";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { AiFillCamera } from "react-icons/ai";

import * as S from "./ProfileEditStyle";

export default function ProfileEdit() {
  const [image, setImage] = useState<string | null>(null);
  const {
    register,
    handleSubmit,
    formState: { isValid },
  } = useForm({
    mode: "onChange",
  });

  const onSubmit = () => {};

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
        <S.EditForm onSubmit={handleSubmit(onSubmit)}>
          <S.NicknameInput
            {...register("nickname", { required: true })}
            type="text"
          />
          <S.MemberDescriptionTextarea
            {...register("description", { required: false, maxLength: 100 })}
          />
          <S.SubmitButton isValid={isValid} type="submit" value="Submit" />
        </S.EditForm>
      </S.ProfileEditContainer>
    </>
  );
}
