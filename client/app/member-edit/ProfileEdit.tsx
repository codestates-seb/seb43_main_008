"use client";

import { useForm } from "react-hook-form";
import styled from "styled-components";

export default function ProfileEdit() {
  const {
    register,
    handleSubmit,
    formState: { isValid },
  } = useForm({
    mode: "onChange",
  });

  const onSubmit = () => {};

  return (
    <ProfileEditContainer>
      <EditForm onSubmit={handleSubmit(onSubmit)}>
        <NicknameInput
          {...register("nickname", { required: true })}
          type="text"
        />
        <MemberDescriptionTextarea
          {...register("description", { required: false, maxLength: 100 })}
        />
        <SubmitButton isValid={isValid} type="submit" value="Submit" />
      </EditForm>
    </ProfileEditContainer>
  );
}

const ProfileEditContainer = styled.div`
  width: 100%;
  padding: 10px 24px;
`;

const EditForm = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const NicknameInput = styled.input`
  width: 100%;
  height: 45px;
  margin-bottom: 10px;
  border: none;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 3px 15px;
`;

const MemberDescriptionTextarea = styled.textarea`
  width: 100%;
  height: 100px;
  margin-bottom: 10px;
  border: none;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 10px 15px;
  resize: none;

  @media (min-width: 800px) {
    height: 450px;
  }
`;

const SubmitButton = styled.input<{ isValid: boolean }>`
  height: 100%;
  width: 100%;
  color: ${(props) => (props.isValid ? "#3f910c" : "#85858e")};
  background-color: ${(props) => (props.isValid ? "#eff4e7" : "#f5f2f0")};
  border-radius: 16px;
  border: ${(props) =>
    props.isValid ? "1px solid #3f910c" : "solid 1px #85858e"};

  cursor: ${(props) => (props.isValid ? "pointer" : "not-allowed")};
  transition: all ease-out 1.2s;
`;
