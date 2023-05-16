"use client";

import { useEffect, useState } from "react";
import styled from "styled-components";

export default function Header() {
  const [phoneNumber, setPhoneNumber] = useState("");
  const [regexPass, setRegexPass] = useState(false);

  const handleExtractNumberFromValue = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    setPhoneNumber(e.target.value.replace(/[^0-9]/g, ""));
  };

  const submitHandler = () => {
    console.log("submitHandler");
  };

  useEffect(() => {
    const regPhone = /^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$/;
    if (regPhone.test(phoneNumber)) {
      setRegexPass(true);
    } else {
      setRegexPass(false);
    }
  }, [phoneNumber]);

  return (
    <>
      <InputContainer>
        <PhoneNumberInput
          type="tel"
          value={phoneNumber}
          maxLength={11}
          placeholder="휴대폰 번호를 -없이 숫자만 입력해주세요."
          onChange={handleExtractNumberFromValue}
        />
      </InputContainer>
      {regexPass ? null : (
        <RegexErrorSpan> 형식이 일치하지 않습니다.</RegexErrorSpan>
      )}
      <ButtonContainer>
        <PhoneNumberSubmitButton
          onClick={submitHandler}
          disabled={!regexPass}
          regexPass={regexPass}
        >
          인증하기
        </PhoneNumberSubmitButton>
      </ButtonContainer>
    </>
  );
}

const InputContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px 15px 5px 15px;
`;

const PhoneNumberInput = styled.input`
  width: 100%;
  height: 44px;
  padding: 3px 8px;
`;

const RegexErrorSpan = styled.span`
  color: red;
  font-size: 12px;
  margin-left: 15px;
`;

const ButtonContainer = styled.div`
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

interface AuthNumberSubmitButtonProps {
  disabled: boolean;
  regexPass: boolean;
}

const PhoneNumberSubmitButton = styled.button<AuthNumberSubmitButtonProps>`
  height: 100%;
  width: 100%;
  border: none;
  border-radius: 4px;
  background-color: ${(props) => (props.regexPass ? "#0078ff" : "#b6b6b8")};
  color: #ffffff;
  cursor: ${(props) => (props.regexPass ? "pointer" : "not-allowed")};
`;
