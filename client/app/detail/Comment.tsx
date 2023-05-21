"use client";

import { useCallback, useState } from "react";
import { MdMoreVert } from "react-icons/md";
import styled from "styled-components";

import { Modal } from "./Modal";

// pagedata.id = comment id 
// pagedata.member.id = member id 
// mine = true

interface CommentProps {
  id?: string
  comment: string;
  member: { nickName: string }
  mine: boolean;
  handleEditComment: (id: string) => void;
  setComment: React.Dispatch<React.SetStateAction<string>>
  setUpdate: React.Dispatch<React.SetStateAction<boolean>>
  update: boolean
}
export const Comment: React.FC<CommentProps> = ({
  member,
  id,
  comment,
  mine,
  handleEditComment,
  setComment,
  setUpdate,
  update,
}) => {

  // 댓글 날짜 추가하기(?)

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
            <div className="user-name">{member.nickName}</div>
            <div className="text">{comment}</div>
          </div>
          {mine ?
            <MdMoreVert
              className="edit-delete-button"
              onClick={HandleOpenModal}
            />
            :
            null
          }
        </li>
      </StyledComment>
      {isOpenModal && (
        <Modal
          onClickModal={HandleOpenModal}
          commentId={id}
          handleEditComment={handleEditComment}
          setComment={setComment}
          setUpdate={setUpdate}
          update={update}
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
