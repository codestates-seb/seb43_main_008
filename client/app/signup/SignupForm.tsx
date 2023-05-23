"use client";

import axios from "axios";
import { useRouter, useSearchParams } from "next/navigation";
import { useEffect, useState } from "react";
import { SubmitHandler, useForm } from "react-hook-form";
import styled from "styled-components";

type FormValues = {
  nickName: string;
  phone: string;
};

export default function RegisterForm() {
  useEffect(() => {
    // Hide URL
    history.replaceState({}, null, location.pathname);
  }, []);

  const {
    register,
    handleSubmit,
    formState: { errors, isValid },
    watch,
  } = useForm<FormValues>({
    mode: "onChange",
  });

  const router = useRouter();
  const submitRegister: SubmitHandler<FormValues> = async (data) => {
    try {
      const accessToken = searchParams.get("Access");
      const authorizationToken = `Bearer ${accessToken}`;
      const response = await axios.post(
        `${process.env.NEXT_PUBLIC_API_URL}/signup`,
        data,
        {
          headers: {
            Authorization: authorizationToken,
          },
        }
      );
      if (response.data.message === "닉네임이 중복이예요.") {
        setnickNameDuplicate("닉네임이 중복이예요.");
      } else if (response.data.message === "휴대폰 번호가 중복이예요.") {
        setnickNameDuplicate("");
        setPhoneDuplicate("휴대폰 번호가 중복이예요.");
      } else {
        const loginToken = response.headers["authorization"];
        const refreshToken = response.headers["refresh"];
        localStorage.setItem("Authorization", loginToken);
        localStorage.setItem("RefreshToken", refreshToken);
        router.push("/signup-congratulations");
      }
    } catch (error) {
      console.log(error);
    }
  };

  const [nickNameDuplicate, setnickNameDuplicate] = useState("");
  const [phoneDuplicate, setPhoneDuplicate] = useState("");
  const watchedNickname = watch("nickName");
  const watchedPhoneNumber = watch("phone");
  const searchParams = useSearchParams();
  const emailValue = searchParams.get("email");

  return (
    <form onSubmit={handleSubmit(submitRegister)}>
      <InputContainer>
        <Label htmlFor="email">이메일</Label>
        <Input defaultValue={emailValue} disabled readOnly id="email" />
      </InputContainer>

      <InputContainer>
        <Label htmlFor="nickname">닉네임</Label>
        <Input
          {...register("nickName", {
            required: true,
            minLength: 2,
            maxLength: 10,
          })}
          id="nickname"
          placeholder="2글자 이상, 10글자 이하로 작성해주세요."
        />
      </InputContainer>

      {watchedNickname && errors.nickName && (
        <ErrorSpan>2글자 이상, 10글자 이하로 작성해주세요.</ErrorSpan>
      )}
      {nickNameDuplicate && <ErrorSpan>{nickNameDuplicate}</ErrorSpan>}

      <InputContainer>
        <Label htmlFor="phone">휴대전화 번호</Label>
        <Input
          {...register("phone", {
            required: true,
            minLength: 9,
            maxLength: 11,
          })}
          placeholder="휴대폰 번호를 -없이 숫자만 입력해주세요."
          maxLength={11}
          type="tel"
          id="phone"
        />
      </InputContainer>

      {watchedPhoneNumber && errors.phone && (
        <ErrorSpan>휴대폰 번호 형식이 일치하지 않습니다.</ErrorSpan>
      )}
      {phoneDuplicate && <ErrorSpan>{phoneDuplicate}</ErrorSpan>}
      <SubmitButtonContainer>
        <SubmitButton type="submit" isValid={isValid} disabled={!isValid} />
      </SubmitButtonContainer>
    </form>
  );
}

const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  padding: 10px 15px 5px 15px;
`;

const Label = styled.label`
  margin-bottom: 5px;
`;

const Input = styled.input`
  width: 100%;
  height: 44px;
  padding: 3px 8px;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
`;

const ErrorSpan = styled.span`
  color: red;
  font-size: 12px;
  margin-left: 15px;
`;

const SubmitButtonContainer = styled.div`
  position: absolute;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  max-width: 1024px;
  width: 100%;
  height: 60px;
  padding: 10px 15px;
  border-radius: 4px;
`;

const SubmitButton = styled.input<{ isValid: boolean }>`
  height: 100%;
  width: 100%;
  /* color: #85858e;
  border: none;
  border-radius: 4px;
  background-color: ${(props) => (props.isValid ? "#0078ff" : "#b6b6b8")};
  color: #ffffff; */
  color: ${(props) => (props.isValid ? "#3f910c" : "#85858e")};
  background-color: ${(props) => (props.isValid ? "#eff4e7" : "#f5f2f0")};
  border-radius: 16px;
  border: ${(props) =>
    props.isValid ? "1px solid #3f910c" : "solid 1px #85858e"};

  cursor: ${(props) => (props.isValid ? "pointer" : "not-allowed")};
  transition: all ease-out 1.2s;
`;
