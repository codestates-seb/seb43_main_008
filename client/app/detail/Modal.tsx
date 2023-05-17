"use client";

import { AiOutlineMinus } from "react-icons/ai";
import { RiDeleteBinLine, RiEdit2Line } from "react-icons/ri";
import styled from "styled-components";

interface Props {
  onClickModal: () => void;
  handleEditComment: (id: number) => void;
  commentid: number;
  setComment: React.Dispatch<React.SetStateAction<string>>

}

export const Modal: React.FC<Props> = ({
  onClickModal,
  handleEditComment,
  commentid,
  setComment,
}) => {

  const HandleDelete = () => {
    onClickModal();
    setComment("")
    console.log("서버에 삭제 요청 보내기");
  };

  const HandleEdit = () => {
    onClickModal();
    handleEditComment(commentid);
  };

  return (
    <StyledModal onClick={onClickModal}>
      <div className="box" onClick={(e) => e.stopPropagation()}>
        <div className="title">
          <div className="text">댓글</div>
          <AiOutlineMinus className="bar-icon" onClick={onClickModal} />
        </div>
        <div className="edit">
          <RiEdit2Line className="icon" />
          <div className="text" onClick={HandleEdit}>
            수정하기
          </div>
        </div>
        <div className="delete" onClick={HandleDelete}>
          <RiDeleteBinLine className="icon" />
          <div className="text">삭제하기</div>
        </div>
      </div>
    </StyledModal>
  );
};

const StyledModal = styled.div`
  z-index: 100;
  background-color: rgba(45, 45, 45, 0.5);

  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100vh;

  display: flex;
  align-items: flex-end;

  color: #222;
  font-size: 1.15rem;

  .box {
    bottom: 0;
    width: 100%;
    height: 150px;

    margin: 32px 12px;
    padding: 6px 12px 12px 12px;

    border-radius: 12px;
    background-color: white;

    display: flex;
    flex-direction: column;
    justify-content: space-around;
  }

  .title {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: flex-start;
  }
  .bar-icon {
    position: fixed;
    bottom: 150px;
    right: 10vw;
    width: 80%;
    font-size: 2.5rem;
    color: #757575;
  }

  .edit,
  .delete {
    cursor: pointer;
    display: flex;
    flex-direction: row;
  }
  .icon {
    width: 3rem;
  }
`;
