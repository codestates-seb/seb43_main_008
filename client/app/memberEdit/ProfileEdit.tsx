"use client";

import styled from "styled-components";

export default function ProfileEdit() {
  return (
    <ProfileEditContainer>
      <EditForm>
        <NicknameInput type="text" />
        <MemberDescriptionTextarea />
      </EditForm>
    </ProfileEditContainer>
  );
}

const ProfileEditContainer = styled.div`
  width: 100%;
  padding: 10px;
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
  outline: none;
`;

const MemberDescriptionTextarea = styled.textarea`
  width: 100%;
  height: 350px;
  margin-bottom: 10px;
  border: none;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 10px 15px;
  outline: none;
  resize: none;

  @media (min-width: 800px) {
    height: 450px;
  }
`;
