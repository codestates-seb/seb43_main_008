"use-client"
import styled from 'styled-components'

import { data } from "./data"
import { Medal } from './Medal'

interface Props {
  level?: number;
  voting?: boolean;
}

export const VotingPost: React.FC<Props> = ({ level, voting }) => {
  return (
    <StyledVoting>
      <div className='box'>
        <div className="image" style={{ backgroundImage: `url(${data.image})` }}> {voting ? <div className='voting'>투표중</div> : <div className='voting'>투표완료</div>}</div>
      </div>
      <div className='text'> 세바스찬 </div>
      {level ? <Medal /> : null}
    </StyledVoting>
  )
}

const StyledVoting = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  .text{
    font-size: 0.8rem;
    color: #757575;
  }
  .box{
    border-radius: 8px;
    border: solid 2px #A9907E;
    box-shadow: 6px 6px #FFEAD2, -6px 6px #FFEAD2, -6px -6px #FFEAD2, 6px -6px #FFEAD2;
    /* border: solid 2px rgb(10, 149, 255);
    box-shadow: 6px 6px rgb(209, 235, 253), -6px 6px rgb(202, 235, 253), -6px -6px rgb(202, 235, 253), 6px -6px rgb(202, 235, 253); */
    margin-bottom: 8px;
    padding: 0;
  }
  .image {
    filter: grayscale(80%);

    width: calc(74vw / 3);
    max-width: 256px;
    height: calc(74vw / 3);

    background-position: center;
    background-size: cover;
    border-radius: 5px;

    display: flex;
    justify-content: center;
    align-items: center;
    .voting{
      font-size: 4.5vw;
      color: white;
      text-shadow: #878787 3px 3px 5px, #878787 -3px 3px 5px, #878787 -3px -3px 5px, #878787 3px -3px 5px;

    }
  }
  @media screen and (min-width: 1024px) {
      width: 270px;
      height: 270px;
      font-size: 46px;
  }
`