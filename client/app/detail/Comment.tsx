"use client";

import styled from "styled-components";

import { StyledCard } from "./Card";

export const Comment = () => {
  return (
    <StyledComment>
      <div className="title">
        <h3 className="sub-title">댓글</h3>
        <div className="sub-text"> 30개</div>
      </div>

      <StyledCard>
        <div className="content">
          <label className="add-comment">
            <div className="profile" />
            <textarea className="input" placeholder="댓글 추가" />
          </label>
          <ul className="comment-box">
            <li className="comment">
              <div className="profile" />
              <div className="text-box">
                <div className="user-name">봉다리</div>
                <div className="text">비닐봉다리 백번 써볼까요</div>
              </div>
              <div>...</div>
            </li>
            <li className="comment">
              <div className="profile" />
              <div className="text-box">
                <div className="user-name ">봉다리</div>
                <div className="text">비닐봉다리 백번 써볼까요</div>
              </div>
              <div>...</div>
            </li>
            <li className="comment">
              <div className="profile" />
              <div className="text-box">
                <div className="user-name ">봉다리</div>
                <div className="text">비닐봉다리 백번 써볼까요</div>
              </div>
              <div>...</div>
            </li>
          </ul>
        </div>
      </StyledCard>
    </StyledComment>
  );
};

const StyledComment = styled.div`
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
  .comment {
    width: 100%;
    padding: 24px 0;

    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: flex-start;
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
  .profile {
    width: 26px;
    height: 26px;

    border-radius: 50%;
    background-color: #757575;
  }
  .input {
    height: 28px;
    width: 100%;
    margin-left: 16px;
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
`;
