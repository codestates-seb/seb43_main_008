"use client";

import styled from "styled-components";
import { useState, useCallback } from "react";
import { MdMoreVert } from "react-icons/md";
import { Modal } from "./Modal";

export const Comment: React.FC = () => {
  // 사용자 Id === 댓글 작성자 Id : edit-delete-button 활성화 하기

  const [isOpenModal, setOpenModal] = useState<boolean>(false);
  const HandleOpenModal = useCallback(() => {
    setOpenModal(!isOpenModal);
  }, [isOpenModal]);

  return (
    <>
      <StyledComment>
        <li className="comment">
          <div className="profile" />
          <div className="text-box">
            <div className="user-name">봉다리</div>
            <div className="text">비닐봉다리 백번 써볼까요</div>
          </div>
          <MdMoreVert
            className="edit-delete-button"
            onClick={HandleOpenModal}
          />
        </li>
      </StyledComment>
      {isOpenModal && <Modal onClickModal={HandleOpenModal} />}
    </>
  );
};

const StyledComment = styled.div`
  .comment {
    width: 100%;
    padding: 24px 0;

    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: flex-start;
  }
  .profile {
    min-width: 26px;
    height: 26px;

    border-radius: 50%;
    background-color: #757575;
  }
  .user-name {
    font-size: 0.8rem;
    color: #757575;
    padding-bottom: 6px;
  }
  .text-box {
    width: 100%;
    margin-left: 16px;
  }
  .edit-delete-button {
    font-size: 1.3rem;
    cursor: pointer;
  }
`;
