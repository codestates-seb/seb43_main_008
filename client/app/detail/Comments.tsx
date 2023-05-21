"use client";
import { useParams } from 'next/navigation';
import React, { useEffect, useState } from "react";
import { BsSend } from "react-icons/bs";
import styled from "styled-components";

import { GetComment, PatchComment, PostComment } from "../api/commentApi"
import { StyledCard } from "./Card";
import { Comment } from "./Comment";
export const Comments: React.FC = () => {

  // 댓글 <생성, 수정, 삭제>시 댓글 리스트 업데이트를 위한 함수
  const [update, setUpdate] = useState<boolean>(false);

  // 서버에서 받아오는 댓글 리스트
  const [commentList, setCommentList] = useState([]);
  const params = useParams();

  useEffect(() => {
    console.log("GetComment 요청")
    console.log(`GetComment : ${update}`)
    GetComment(params.id).then((data) => {
      if (data) {
        setCommentList((data))
        console.log(data)
      }
    })
  }, [update]) // 📌 상태는 변경되는데 GetComment는 한템포 늦음

  // 댓글 입력 및 서버 전달을 위한 상태 & 함수
  const [comment, setComment] = useState<string>("");

  const handleInputChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setComment(e.target.value);
  };

  const handleSubmit = (
    e: React.FormEvent<HTMLFormElement | HTMLTextAreaElement>
  ) => {
    e.preventDefault();
    if (editCommentId !== undefined && comment.length > 2) {
      PatchComment(params.id, editCommentId, comment) // 댓글 수정 요청 보내기
      setEditCommentId(undefined) // 수정 댓글 id 리셋하기
      GetComment(params.id) // 📌 왜 update 상태만으로 안되지? 
      setUpdate(!update)
      setComment("");
    }
    else if (comment.length > 2) { // 두글자 초과 입력시에만 댓글 저장 가능
      PostComment(params.id, comment) // 새로운 댓글 보내기
      // GetComment(params.id) // 📌 왜 update 상태만으로 안되지? 
      setUpdate(!update)
      setComment("");
      console.log(`handleSubmit : ${update}`)
    }
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLTextAreaElement>) => {
    if (e.key === "Enter" && !e.shiftKey) {
      handleSubmit(e);
    }
  };

  // 댓글 수정을 위한 함수
  const [editCommentId, setEditCommentId] = useState<string | undefined>(
    undefined
  );

  console.log(editCommentId)

  const handleEditComment = (id: string) => {
    setEditCommentId(id);
  };

  const editCommentData = commentList.find((data) =>
    data.id === Number(editCommentId)
  );

  useEffect(() => {
    setComment(editCommentData?.comment);
  }, [editCommentData]);

  return (
    <StyledComments>
      <div className="title">
        <h3 className="sub-title">댓글</h3>
        <div className="sub-text"> 30개</div>
      </div>

      <StyledCard>
        <div className="content">
          <form className="add-comment" onSubmit={handleSubmit}>
            <div className="profile" />
            <textarea
              className="input"
              placeholder="댓글 추가"
              value={comment}
              onChange={handleInputChange}
              onKeyDown={handleKeyDown}
            />
            <button className="submit" type="submit">
              <BsSend />
            </button>
          </form>

          <ul className="comment-box">

            {commentList.length > 0 ?
              commentList.map((data) => (
                <Comment
                  key={data.id}
                  {...data}
                  handleEditComment={handleEditComment}
                  setComment={setComment}
                  setUpdate={setUpdate}
                  update={update}
                />
              ))
              : <div> 작성된 댓글이 없습니다. </div>}

          </ul>
        </div>
      </StyledCard>
    </StyledComments>
  );
};

const StyledComments = styled.div`
  margin-bottom: 100px;
  .title {
    margin: 0 24px;
    display: flex;
    flex-direction: row;
    align-items: baseline;

    .sub-text {
      margin-left: 6px;
      font-size: 0.8rem;
      color: #757575;
    }
  }

  .profile {
    min-width: 26px;
    height: 26px;

    border-radius: 50%;
    background-color: #757575;
  }

  .add-comment {
    width: 100%;
    padding-bottom: 21px;
    margin-bottom: 12px;
    border-bottom: solid 0.1px #757575;

    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: flex-start;
  }

  .input {
    height: 28px;
    width: 90%;
    margin-left: 10px;
    padding: 6px 12px;

    background-color: #f2f2f2;
    resize: none;
    border: none;
    border-radius: 16px;

    ::-webkit-scrollbar {
      display: none;
    }

    &::placeholder {
      font-size: 0.8rem;
      font-weight: 900;
      color: #757575;
    }
    &:focus {
      outline: none;
      height: auto;
    }
  }

  .submit {
    margin-top: 6px;
    border: none;
    background-color: white;
    font-size: 1rem;

    cursor: pointer;
  }
`;
