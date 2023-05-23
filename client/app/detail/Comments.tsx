"use client";
import { useParams } from 'next/navigation';
import React, { useEffect, useState } from "react";
import { BsSend } from "react-icons/bs";
import styled from "styled-components";

import { DeleteComment, GetComment, PatchComment, PostComment } from "../api/commentApi"
import { StyledCard } from "./Card";
import { Comment } from "./Comment";
export const Comments: React.FC = () => {

  // 서버에서 받아오는 댓글 리스트
  const [commentList, setCommentList] = useState([]);
  const params = useParams();

  useEffect(() => {
    GetComment(params.id).then((data) => {
      if (data) {
        setCommentList((data))
      }
    })
  }, [])

  const [comment, setComment] = useState<string>("");

  const handleInputChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setComment(e.target.value);
  };

  // 서버에 댓글 수정 or 생성 요청을 보내는 함수
  const handleSubmit = async (
    e: React.FormEvent<HTMLFormElement | HTMLTextAreaElement>
  ) => {
    e.preventDefault();

    if (comment.length > 2) {
      if (editCommentId !== undefined) {
        await PatchComment(params.id, editCommentId, comment); // 댓글 수정
      } else {
        await PostComment(params.id, comment); // 댓글 생성
      }

      GetComment(params.id).then((data) => {
        if (data) {
          setCommentList(data);
          setEditCommentId(undefined);
          setComment("");
        }
      });
    }
  };

  // 댓글 삭제 핸들러: Comments -> Commnet -> Modal에서 조작한다. 
  const handleDelete = async (commentId: string) => {
    await DeleteComment(params.id, commentId)
    GetComment(params.id).then((data) => {
      if (data) {
        setCommentList(data);
        setComment("");
      }
    });
  }


  // 📌 작동 안함
  const handleKeyDown = (e: React.KeyboardEvent<HTMLTextAreaElement>) => {
    if (e.key === "Enter" && !e.shiftKey) {
      handleSubmit(e);
    }
  };

  // 댓글 수정을 위한 함수
  const [editCommentId, setEditCommentId] = useState<string | undefined>(
    undefined
  );

  const handleEditComment = (id: string) => {
    setEditCommentId(id); // (1) Modal 컴포넌트에서 editCommentData를 보내주면 
  };

  const editCommentData = commentList.find((data) =>
    data.id === Number(editCommentId) // (2) editCommentId와 일치하는 데이터를 찾아 저장한다. 
  );

  useEffect(() => {
    setComment(editCommentData?.comment); // (3) 댓글 상태도 업데이트 한다.
  }, [editCommentData]);

  return (
    <StyledComments>
      <div className="title">
        <h3 className="sub-title">댓글</h3>
        <div className="sub-text"> {commentList.length}개</div>
      </div>

      <StyledCard>
        <div className="content">
          <form className="add-comment" onSubmit={handleSubmit}>
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
                  handleDelete={handleDelete}
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
