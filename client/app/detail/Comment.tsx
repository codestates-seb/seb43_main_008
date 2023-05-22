"use client";

import { useCallback, useState } from "react";
import { MdMoreVert } from "react-icons/md";
import styled from "styled-components";

import { Modal } from "./Modal";


interface CommentProps {
  id?: string;
  comment: string;
  member: { nickName: string, image: string };
  mine: boolean;
  handleEditComment: (id: string) => void;
  handleDelete: (commentId: string) => void;
  setComment: React.Dispatch<React.SetStateAction<string>>;

}
export const Comment: React.FC<CommentProps> = ({
  member,
  id,
  comment,
  mine,
  handleEditComment,
  handleDelete,
}) => {

  const [isOpenModal, setOpenModal] = useState<boolean>(false);
  const HandleOpenModal = useCallback(() => {
    setOpenModal(!isOpenModal);
  }, [isOpenModal]);

  return (
    <>
      <StyledComment>
        <li className="comment">
          <div className="text-box">
            <div className='user-info'>
              <div className="user-name">{member.nickName}</div>
              {mine ? <div className='mine-tag'>내 댓글</div> : null}
            </div>
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
          handleDelete={handleDelete}
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
  .user-info {
    font-size: 0.8rem;
    color: #757575;
    padding-bottom: 6px;

    display: flex;
    align-items: center;
  }
  .mine-tag {
    margin-left: 5px;
    padding: 1px 2px;

    font-size: 0.45rem;
    color: #3f910c;
    background-color: #eff4e7;
    border: solid 1px #3f910c;
    border-radius: 6px;
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
