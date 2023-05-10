"use client";

import { useCallback, useState } from "react";
import { MdMoreVert } from "react-icons/md";
import styled from "styled-components";

import { Modal } from "./Modal";

interface CommentProps {
  id: number;
  member_id: number;
  series_id: number;
  comment: string;
  created_at: string;
  handleEditComment: (id: number) => void;
}
export const Comment: React.FC<CommentProps> = ({
  member_id,
  comment,
  id,
  handleEditComment,
}) => {
  // 댓글 날짜 추가하기
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
            <div className="user-name">{member_id}</div>
            <div className="text">{comment}</div>
          </div>
          <MdMoreVert
            className="edit-delete-button"
            onClick={HandleOpenModal}
          />
        </li>
      </StyledComment>
      {isOpenModal && (
        <Modal
          onClickModal={HandleOpenModal}
          commentid={id}
          handleEditComment={handleEditComment}
        />
      )}
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
