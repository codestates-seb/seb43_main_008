"use client";
import Image from "next/image";
import Link from "next/link";
import { useParams } from 'next/navigation';
import { useEffect, useState } from "react";
import { AiOutlineSetting } from "react-icons/ai";
import { BsAward } from "react-icons/bs";
import styled from "styled-components";

import { GetMyProfile, GetProfile } from "../api/myPageApi"
import { FollowerButton, FollowingButton } from "./FollowButton";
import { ProfileData } from "./type";

interface Props {
  type?: string
}

export const Profile: React.FC<Props> = ({ type }) => {
  const [profile, setProfile] = useState<ProfileData>({
    nickName: "",
    image: "",
    introduce: "",
  });
  const params = useParams();
  const nickName = decodeURIComponent(params.nickName)

  useEffect(() => {
    if (type === "mine") {
      GetMyProfile().then((data) => {
        if (data) {
          setProfile(data);
        }
      });
    }
    else GetProfile(nickName).then((data) => {
      if (data) {
        setProfile(data);
      }
    });
  }, []);

  return (
    <StyledProfile className="box">
      <div className="profile-box">
        <Image
          className="image"
          src={profile.image}
          width={40}
          height={40}
          alt="프로필 사진"
        />
        <div className="info-box">
          <div className="nick-name">{profile.nickName}</div>
          <div className="user-info">{profile.introduce}</div>
        </div>
        {type === "mine" ? <div className="button-box">
          <Link href="/badge" className="badge">
            <BsAward className="icon" />
            <div className="text">뱃지 보러가기</div>
          </Link>
          <Link href="/member-edit" className="setting">
            <AiOutlineSetting className="icon" />
            <div className="text">정보 수정하기</div>
          </Link>
        </div> : null}
      </div>
      {type === "mine"
        ? <FollowerButton />
        : <FollowingButton followedMember={profile.followedMember} nickName={profile.nickName} />
      }
    </StyledProfile>
  );
};

const StyledProfile = styled.div`
  background-color: #f0f0f0;
  height: auto;
  padding: 24px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;

  a {
    color: inherit;
    text-decoration: none;
  }

  .profile-box {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: flex-start;

    padding-bottom: 24px;
  }

  .info-box {
    flex-grow: 8;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    margin-right: 18px;
  }
  .user-info {
    margin-top: 4.3px;
    font-size: 0.8rem;
    color: #757575;
  }

  .button-box {
    display: flex;
    flex-direction: column;
  }

  .badge,
  .setting {
    cursor: pointer;
    margin-left: 10px;
    margin-bottom: 8px;
    display: flex;
    flex-direction: row;
  }

  .text {
    margin-left: 2px;
    font-size: 0.8rem;
    color: #757575;
  }

  .image {
    border-radius: 50%;
    margin-right: 18px;

    background-position: center;
    background-size: cover;
    border-radius: 50%;
  }
`;
