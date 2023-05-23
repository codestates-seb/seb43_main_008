"use client";

import styled from "styled-components";

export const ProfileImageEditContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  height: 120px;
  padding: 10px 24px;
`;

export const EditDescriptionContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

export const EditDescriptionH1 = styled.h1`
  font-size: 17px;
  font-weight: 700;
  margin-bottom: 7px;
`;

export const EditDescription = styled.p`
  color: #757575;
  font-size: 11px;
`;

export const ImageContainer = styled.div`
  position: relative;
  height: 60px;
  width: 60px;
`;

export const CameraIconContainer = styled.div`
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

export const ImageEditInput = styled.input`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  cursor: pointer;
`;

export const DefaultImage = styled.div`
  height: 60px;
  width: 60px;
  border-radius: 50%;
  background-color: #676767;
`;

export const ProfileEditContainer = styled.div`
  width: 100%;
  padding: 10px 24px;
  margin-bottom: 88px;
`;

export const EditForm = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

export const NicknameInput = styled.input`
  width: 100%;
  height: 45px;
  margin-bottom: 10px;
  border: none;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 3px 15px;
`;

export const MemberDescriptionTextarea = styled.textarea`
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

export const SubmitButton = styled.button`
  height: 50px;
  width: 100%;
  color: #85858e;
  border: none;
  border-radius: 4px;
  background-color: #0078ff;
  color: #ffffff;
  cursor: "pointer";
  transition: all ease-out 1.2s;
`;
