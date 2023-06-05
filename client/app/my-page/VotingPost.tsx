"use-client"
import { useRouter } from "next/navigation";
import styled from 'styled-components'

import { Medal } from './Medal'
import { StyledPost } from "./StyledPost"
import { PostData } from "./type"

export const VotingPost = ({ type, isVoteEnded, ...data }: PostData) => {


  const router = useRouter();
  const HandleMoveToDetail = (id: number, title: string) => {
    router.push(`/detail/${id}`);
    sessionStorage.setItem("header", title);
  }
  const HandleMoveToMoreUsing = (id: number) => {
    router.push(`/first-vote-complete/${id}`);
  }

  return (
    <StyledVoting>
      <div className='box'>
        <div className="image" style={{ backgroundImage: `url(${data.image})` }}>
          {isVoteEnded ? (
            <div
              onClick={type === 'mine' ? () => HandleMoveToMoreUsing(data.id) : null}
              className='voting'>투표완료</div>
          ) : (
            <div
              onClick={() => HandleMoveToDetail(data.id, data.title)}
              className='voting'>투표중</div>
          )}
        </div>
      </div>
      <div className='text'>{data.title}</div>
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