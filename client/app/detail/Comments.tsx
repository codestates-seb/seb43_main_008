"use client";

import React, { useEffect, useState } from "react";
import { BsSend } from "react-icons/bs";
import styled from "styled-components";

import { StyledCard } from "./Card";
import { Comment } from "./Comment";
import { comments } from "./commentData";
export const Comments: React.FC = () => {
  // 댓글 입력 및 서버 전달을 위한 상태 & 함수
  const [comment, setComment] = useState<string>("");

  const handleInputChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setComment(e.target.value);
  };

  const handleSubmit = (
    e: React.FormEvent<HTMLFormElement | HTMLTextAreaElement>
  ) => {
    e.preventDefault();
    if (comment.length !== 0) {
      setComment("");
      console.log("서버에 데이터 보내고 & 댓글 리스트 새로 가져와야함");
    }
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLTextAreaElement>) => {
    if (e.key === "Enter" && !e.shiftKey) {
      handleSubmit(e);
    }
  };

  // 댓글 수정을 위한 함수
  const [editCommentId, setEditCommentId] = useState<number | undefined>(
    undefined
  );

  const handleEditComment = (id: number) => {
    setEditCommentId(id);
  };

  const editCommentData = comments.find((data) => data.id === editCommentId);

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
            {comments.map((data) => {
              return (
                <Comment
                  key={data.id}
                  {...data}
                  handleEditComment={handleEditComment}
                  setComment={setComment}
                />
              );
            })}
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
