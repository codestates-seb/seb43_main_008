"use-client"
import { useRouter } from "next/navigation";
import styled from 'styled-components'

import { Medal } from './Medal'
import { StyledPost } from "./StyledPost"
import { PostData } from "./type"

export const VotingPost = ({ ...data }: PostData) => {

  const router = useRouter();
  const HandleMoveToDetail = (id: number, title: string) => {
    router.push(`/detail/${id}`);
    sessionStorage.setItem("header", title);
    sessionStorage.setItem("menu", null);
  }

  return (
    <StyledVoting onClick={() => HandleMoveToDetail(data.id, data.title)}>
      <div className='box'>
        <div className="image" style={{ backgroundImage: `url(${data.image})` }}> {data.voteResult === false ? <div className='voting'>투표완료</div> : <div className='voting'>투표중</div>}</div>
      </div>
      <div className='text'> {data.title} </div>
      {data.voteResult ? <Medal /> : null}
    </StyledVoting>
  )
}

const StyledVoting = styled(StyledPost)`
  .image {    
    filter: grayscale(90%);
  }
  .voting{
    font-size: 4.5vw;
    color: white;
    text-shadow: #878787 3px 3px 5px, #878787 -3px 3px 5px, #878787 -3px -3px 5px, #878787 3px -3px 5px;
  }

`